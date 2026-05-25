package com.doug.pointofsale.payload.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentResponse {
    String redirectUrl;
    String pollUrl;
    String invoiceNumber;
    String message;

}
