package com.doug.pointofsale.service.Impl;

import com.doug.pointofsale.mapper.ShiftReportMapper;
import com.doug.pointofsale.models.Branch;
import com.doug.pointofsale.models.ShiftReport;
import com.doug.pointofsale.models.User;
import com.doug.pointofsale.payload.dto.ShiftReportDTO;
import com.doug.pointofsale.repository.ShiftReportRepository;
import com.doug.pointofsale.service.ShiftReportService;
import com.doug.pointofsale.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ShiftReportImpl implements ShiftReportService {

    private final ShiftReportRepository shiftReportRepository;
    private final UserService userService;

    public ShiftReportImpl(ShiftReportRepository shiftReportRepository, UserService userService) {
        this.shiftReportRepository = shiftReportRepository;
        this.userService = userService;
    }

    @Override
    public ShiftReportDTO startShift(Long cashierId, Long branchId, LocalDateTime shiftStart) throws Exception {
        User currentUser = userService.getCurrentUser();
        shiftStart = LocalDateTime.now();
        LocalDateTime startOfDay = shiftStart.withHour(0).withMinute(0).withSecond(0);
        LocalDateTime endOfDay = shiftStart.withHour(23).withMinute(59).withSecond(59);
        Optional<ShiftReport> existing = shiftReportRepository.findTopByCashierAndShiftStartBetween(
                currentUser, startOfDay, endOfDay);
        if (existing.isPresent()) {
            throw new Exception("Shift report already exists");
        }
        Branch branch = currentUser.getBranch();

        ShiftReport shiftReport = new ShiftReport();
        shiftReport.setCashier(currentUser);
        shiftReport.setShiftStart(startOfDay);
        shiftReport.setBranch(branch);
        ShiftReport savedReport =  shiftReportRepository.save(shiftReport);


        return ShiftReportMapper.toDTO(savedReport);
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
