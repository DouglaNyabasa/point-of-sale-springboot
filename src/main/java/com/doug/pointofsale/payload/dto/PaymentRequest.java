package com.doug.pointofsale.payload.dto;

import com.doug.pointofsale.domain.PaymentType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentRequest {

    @Schema(description = "Unique ID of the paying user", example = "1")
    Long userId;
    LocalDateTime bookingDate;
    @Schema(example = "doug@example.com")
    String email;
    @Schema(
            implementation = PaymentType.class,
            type = "string",
            description = "Selected payment type"
    )
    PaymentType paymentType;
}

