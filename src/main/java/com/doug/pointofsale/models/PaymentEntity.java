package com.doug.pointofsale.models;

import com.doug.pointofsale.domain.PaymentType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "payments")
public class PaymentEntity {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     Long id;
     BigDecimal amount;
     String invoiceNumber;
     String reference;
     String redirectUrl;
     String pollUrl;
     String status;
     String email;
     String cartDescription;
     @Enumerated(EnumType.STRING)
     PaymentType type;
     @ManyToOne
     @JoinColumn(name = "user_id", nullable = false)
     User user;
     LocalDateTime paidAt;
     @Builder.Default
     @OneToMany(mappedBy = "paymentEntity", cascade = CascadeType.ALL, orphanRemoval = true)
     List<PaymentItem> items = new ArrayList<>();
}
