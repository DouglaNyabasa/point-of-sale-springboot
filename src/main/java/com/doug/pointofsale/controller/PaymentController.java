package com.doug.pointofsale.controller;

import com.doug.pointofsale.payload.dto.PaymentRequest;
import com.doug.pointofsale.payload.dto.PaymentStatus;
import com.doug.pointofsale.payload.response.PaymentResponse;
import com.doug.pointofsale.payload.response.PaymentStatusDto;
import com.doug.pointofsale.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    /**
     * Initiates a dynamic checkout process for the POS system.
     */
    @PostMapping("/createPayment")
    public ResponseEntity<PaymentResponse> initiatePayment(@RequestBody PaymentRequest request) {
        PaymentResponse response = paymentService.createPayment(request);
        return ResponseEntity.ok(response);
    }

    /**
     * Manual endpoint to check transaction status via client application pulling.
     */
    @GetMapping("/getPaymentStatus")
    public ResponseEntity<PaymentStatus> getStatus(@RequestParam String pollUrl) {
        PaymentStatus status = paymentService.checkPaymentStatus(pollUrl);
        return ResponseEntity.ok(status);
    }

    /**
     * Paynow Asynchronous Result Webhook listener.
     * Processes form-urlencoded POST requests pushed automatically by Paynow's servers.
     */
    @PostMapping(value = "/update", consumes = org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<Void> handlePaynowUpdate(@RequestParam Map<String, String> params) {
        String pollUrl = params.get("pollurl");

        if (pollUrl != null && !pollUrl.isEmpty()) {
            paymentService.checkPaymentStatus(pollUrl);
        }
        return ResponseEntity.ok().build();
    }


}
