package com.doug.pointofsale.service.Impl;

import com.doug.pointofsale.domain.PaymentType;
import com.doug.pointofsale.models.PaymentEntity;
import com.doug.pointofsale.models.User;
import com.doug.pointofsale.payload.dto.PaymentRequest;
import com.doug.pointofsale.payload.dto.PaymentStatus;
import com.doug.pointofsale.payload.response.PaymentResponse;
import com.doug.pointofsale.payload.response.PaymentStatusDto;
import com.doug.pointofsale.repository.PaymentRepository;
import com.doug.pointofsale.repository.UserRepository;
import com.doug.pointofsale.service.PaymentService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import zw.co.paynow.core.Payment;
import zw.co.paynow.core.Paynow;
import zw.co.paynow.responses.WebInitResponse;

import java.time.LocalDateTime;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {
    private final Paynow paynow;
    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;

    public PaymentServiceImpl(Paynow paynow, PaymentRepository paymentRepository, UserRepository userRepository) {
        this.paynow = paynow;
        this.paymentRepository = paymentRepository;
        this.userRepository = userRepository;
    }

    @Override
    public PaymentResponse createPayment(PaymentRequest paymentRequest) {
        // 1. Validate Target Client Account
        User user = userRepository.findById(paymentRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("User account not found"));

        if (paymentRequest.getPaymentType() == null) {
            throw new IllegalArgumentException("A valid payment type selection is mandatory");
        }

        PaymentType chosenPaymentType = paymentRequest.getPaymentType();
        String generatedInvoice = "HQ-SUB-" + user.getId() + "-" + System.currentTimeMillis();

        // 2. Generate the local tracking entry
        PaymentEntity paymentEntity = PaymentEntity.builder()
                .user(user)
                .email(user.getEmail())
                .invoiceNumber(generatedInvoice)
                .type(chosenPaymentType)
                .amount(paymentRequest.getAmount())
                .cartDescription(paymentRequest.getDescription())
                .status("PENDING")
                .build();

        // 3. Bind properties payload to the PayNow transaction framework
        Payment payment = paynow.createPayment(generatedInvoice, user.getEmail());

        // Pass item descriptions and amounts explicitly from payload request safely converted to double
        payment.add(paymentRequest.getDescription(), paymentRequest.getAmount().doubleValue());

        // 4. Request transaction link generation
        WebInitResponse webInitResponse = paynow.send(payment);

        if (webInitResponse.success()) {
            paymentEntity.setStatus("INITIATED");
            paymentEntity.setPollUrl(webInitResponse.getPollUrl());
            paymentEntity.setRedirectUrl(webInitResponse.redirectURL());

            paymentRepository.save(paymentEntity);

            return new PaymentResponse(
                    webInitResponse.redirectURL(),
                    webInitResponse.pollUrl(),
                    generatedInvoice,
                    "Gateway initialized for item: " + paymentRequest.getDescription()
            );
        } else {
            paymentEntity.setStatus("FAILED");
            paymentRepository.save(paymentEntity);
            throw new RuntimeException("PayNow integration channel initialization failed: " + webInitResponse.errors());
        }
    }

    @Override
    public PaymentStatus checkPaymentStatus(String pollUrl) {
        // 1. Fetch live status from Paynow gateway
        zw.co.paynow.responses.StatusResponse statusResponse = paynow.pollTransaction(pollUrl);

        // 2. Safely extract invoice reference tracking key
        String invoiceReference = statusResponse.getRawResponseContent().get("reference");

        if (invoiceReference == null || invoiceReference.isEmpty()) {
            throw new RuntimeException("Paynow status update payload is missing a valid tracking reference key.");
        }

        // 3. Look up original local transaction log
        PaymentEntity payment = paymentRepository.findByInvoiceNumber(invoiceReference)
                .orElseThrow(() -> new RuntimeException("System transaction ledger entry not found for: " + invoiceReference));

        // 4. Evaluate payment outcome state
        if (statusResponse.isPaid() && !"PAID".equals(payment.getStatus())) {
            LocalDateTime now = LocalDateTime.now();

            payment.setStatus("PAID");
            payment.setPaidAt(now);
            paymentRepository.save(payment);
        } else if (!statusResponse.isPaid() && !"PAID".equals(payment.getStatus())) {
            payment.setStatus("FAILED");
            paymentRepository.save(payment);
        }

        boolean isPaid = "PAID".equals(payment.getStatus());
        double finalAmount = payment.getAmount().doubleValue();

        return new PaymentStatus(isPaid, payment.getStatus(), finalAmount);
    }






}
