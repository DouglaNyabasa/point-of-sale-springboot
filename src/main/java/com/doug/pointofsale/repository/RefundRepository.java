package com.doug.pointofsale.repository;

import com.doug.pointofsale.models.Refund;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface RefundRepository extends JpaRepository<Refund, Long> {

    List<Refund> findByCashierAndCreatedBetween(Long cashier, LocalDateTime from, LocalDateTime to);

    List<Refund> findByCashierId(Long id);
    List<Refund> findByShiftReportId(Long id);
    List<Refund> findByBranchId(Long id);
}
