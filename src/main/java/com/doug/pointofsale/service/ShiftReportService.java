package com.doug.pointofsale.service;

import com.doug.pointofsale.models.ShiftReport;
import com.doug.pointofsale.payload.dto.ShiftReportDTO;

import java.time.LocalDateTime;

public interface ShiftReportService {

    ShiftReport startShift(Long cashierId, Long branchId, LocalDateTime shiftStart) throws Exception;
    ShiftReport endShift(Long shiftReportId, LocalDateTime shiftEnd) throws Exception;

}
