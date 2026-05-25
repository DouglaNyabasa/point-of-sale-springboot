package com.doug.pointofsale.repository;


import com.doug.pointofsale.models.PaymentEntity;
import com.doug.pointofsale.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {

    Optional<PaymentEntity> findByInvoiceNumber(String invoiceNumber);

    Optional<PaymentEntity> findFirstByUserAndStatusAndExpiryDateAfter(
            User user, String status, LocalDateTime dateTime);

    @Query("SELECT new com.homequest.dto.response.PaymentStatusDto(p.user.id, p.status, p.amount, p.paidAt, p.expiryDate) " +
            "FROM PaymentEntity p WHERE p.user.id = :userId ORDER BY p.paidAt DESC LIMIT 1")
    PaymentStatusDto findStatusAndExpiryDateByUserId(@Param("userId") Long userId);
}
