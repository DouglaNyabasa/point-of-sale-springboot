package com.doug.pointofsale.controller;


import com.doug.pointofsale.payload.dto.ShiftReportDTO;
import com.doug.pointofsale.service.ShiftReportService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/shift-reports")
public class ShiftController {

    private final ShiftReportService shiftReportService;


    public ShiftController(ShiftReportService shiftReportService) {
        this.shiftReportService = shiftReportService;
    }

    @PostMapping("/start")
    public ResponseEntity<ShiftReportDTO> startShift() throws Exception {
        return ResponseEntity.ok(
                shiftReportService.startShift()
        );
    }

    @PatchMapping("/end")
    public ResponseEntity<ShiftReportDTO> endShift() throws Exception {
        return ResponseEntity.ok(
                shiftReportService.endShift(null,null)
        );
    }

    @GetMapping("/current")
    public ResponseEntity<ShiftReportDTO> getCurrentShiftProgress() throws Exception {
        return ResponseEntity.ok(

                shiftReportService.getCurrentShiftReportsProgress(null)
        );
    }

    @GetMapping("/cashier/{cashierId}/by-date")
    public ResponseEntity<ShiftReportDTO> getShiftReportByDate(@PathVariable Long cashierId, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDateTime date) throws Exception {
        return ResponseEntity.ok(

                shiftReportService.getShiftByCashierAndDate(cashierId,date)
        );
    }


    @GetMapping("/cashier/{cashierId}")
    public ResponseEntity<List< ShiftReportDTO>> getShiftReportByCashier(@PathVariable Long cashierId) throws Exception {
        return ResponseEntity.ok(

                shiftReportService.getShiftReportsByCashierId(cashierId)
        );
    }

    @GetMapping("/branch/{branchId}")
    public ResponseEntity<List< ShiftReportDTO>> getShiftReportByBranch(@PathVariable Long branchId) throws Exception {
        return ResponseEntity.ok(

                shiftReportService.getShiftReportsByBranchId(branchId)
        );
    }







}
