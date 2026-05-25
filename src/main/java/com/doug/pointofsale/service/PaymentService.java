package com.doug.pointofsale.service;

import com.doug.pointofsale.models.User;
import com.doug.pointofsale.payload.dto.PaymentRequest;
import com.doug.pointofsale.payload.dto.PaymentStatus;
import com.doug.pointofsale.payload.response.PaymentResponse;
import com.doug.pointofsale.payload.response.PaymentStatusDto;

public interface PaymentService {

    PaymentResponse createPayment(PaymentRequest paymentRequest);
    PaymentStatus checkPaymentStatus(String pollUrl);
    boolean canUserViewHouses(User user);

    PaymentStatusDto paymentStatus(Long userId);
}
