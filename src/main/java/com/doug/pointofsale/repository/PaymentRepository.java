package com.doug.pointofsale.repository;


import com.doug.pointofsale.models.PaymentEntity;
import com.doug.pointofsale.models.User;
import com.doug.pointofsale.payload.response.PaymentStatusDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {

    Optional<PaymentEntity> findByInvoiceNumber(String invoiceNumber);


}
