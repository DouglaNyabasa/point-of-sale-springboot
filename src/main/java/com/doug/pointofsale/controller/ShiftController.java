package com.doug.pointofsale.controller;


import com.doug.pointofsale.payload.dto.ShiftReportDTO;
import com.doug.pointofsale.service.ShiftReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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





}
