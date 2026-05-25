package com.doug.pointofsale.payload.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PaymentStatusDto(Long userId,

                               String status,
                               BigDecimal amount,
                               LocalDateTime paidAt,
                               LocalDateTime expiryDate) {


}
