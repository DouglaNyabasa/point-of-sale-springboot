package com.doug.pointofsale.repository;

import com.doug.pointofsale.models.ShiftReport;
import com.doug.pointofsale.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ShiftReportRepository extends JpaRepository<ShiftReport, Long> {

    List<ShiftReport> findByCashierId(Long id);
    List<ShiftReport> findByBranchId(Long id);
    Optional<ShiftReport> findTopByCashierAndShiftEndIsNullOrderByShiftStartDesc(User cashier);

    Optional<ShiftReport> findTopByCashierAndShiftStartBetween(User cashier, LocalDateTime start, LocalDateTime end);
    
}

