package com.doug.pointofsale.service;

import com.doug.pointofsale.Exception.UserException;
import com.doug.pointofsale.models.ShiftReport;
import com.doug.pointofsale.payload.dto.ShiftReportDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface ShiftReportService {

    ShiftReportDTO startShift(Long cashierId, Long branchId, LocalDateTime shiftStart) throws Exception;
    ShiftReportDTO endShift(Long shiftReportId, LocalDateTime shiftEnd) throws Exception;
    ShiftReportDTO getShiftReportById(Long shiftReportId) ;
    List<ShiftReportDTO> getAllShiftReports() ;
    List<ShiftReportDTO> getShiftReportsByBranchId(Long branchId) ;
    List<ShiftReportDTO> getShiftReportsByCashierId(Long cashierId) ;
    ShiftReportDTO getCurrentShiftReportsProgress(Long cashierId) throws Exception;
    ShiftReportDTO getShiftByCashierAndDate(Long cashierId, LocalDateTime date) throws Exception;


}
