package com.doug.pointofsale.service.Impl;

import com.doug.pointofsale.models.ShiftReport;
import com.doug.pointofsale.payload.dto.ShiftReportDTO;
import com.doug.pointofsale.repository.ShiftReportRepository;
import com.doug.pointofsale.service.ShiftReportService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ShiftReportImpl implements ShiftReportService {

    private final ShiftReportRepository shiftReportRepository;

    public ShiftReportImpl(ShiftReportRepository shiftReportRepository) {
        this.shiftReportRepository = shiftReportRepository;
    }

    @Override
    public ShiftReportDTO startShift(Long cashierId, Long branchId, LocalDateTime shiftStart) throws Exception {
        return null;
    }

    @Override
    public ShiftReportDTO endShift(Long shiftReportId, LocalDateTime shiftEnd) throws Exception {
        return null;
    }

    @Override
    public ShiftReportDTO getShiftReportById(Long shiftReportId) {
        return null;
    }

    @Override
    public List<ShiftReportDTO> getAllShiftReports() {
        return List.of();
    }

    @Override
    public List<ShiftReportDTO> getShiftReportsByBranchId(Long branchId) {
        return List.of();
    }

    @Override
    public List<ShiftReportDTO> getShiftReportsByCashierId(Long cashierId) {
        return List.of();
    }

    @Override
    public ShiftReportDTO getCurrentShiftReportsProgress(Long cashierId) throws Exception {
        return null;
    }

    @Override
    public ShiftReportDTO getShiftByCashierAndDate(Long cashierId, LocalDateTime date) throws Exception {
        return null;
    }
}
