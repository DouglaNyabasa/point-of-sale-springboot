package com.doug.pointofsale.repository;

import com.doug.pointofsale.models.ShiftReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShiftReportRepository extends JpaRepository<ShiftReport, Long> {
}
