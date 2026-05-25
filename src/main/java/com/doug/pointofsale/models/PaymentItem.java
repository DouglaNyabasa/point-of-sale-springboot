package com.doug.pointofsale.models;

import com.doug.pointofsale.domain.PaymentType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "payment_items")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Enumerated(EnumType.STRING)
    @Column( nullable = false)
    PaymentType name;

    BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "payment_id")
    PaymentEntity paymentEntity;



    @Override
    public String toString() {
        return "PaymentItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
