package com.doug.pointofsale.controller;

import com.doug.pointofsale.models.Refund;
import com.doug.pointofsale.payload.dto.RefundDTO;
import com.doug.pointofsale.service.RefundService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/refunds")
public class RefundController {

    private final RefundService refundService;

    public RefundController(RefundService refundService) {
        this.refundService = refundService;
    }

    @PostMapping("/create")
    public ResponseEntity<RefundDTO> createRefund(@RequestBody RefundDTO refundDTO) throws Exception {
        RefundDTO refund = refundService.createRefund(refundDTO);
        return  ResponseEntity.ok(refund);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<RefundDTO>> getAllRefund() throws Exception {
        List<RefundDTO> refund = refundService.getAllRefund();
        return  ResponseEntity.ok(refund);
    }

    @GetMapping("/cashier/{cashierId}")
    public ResponseEntity<List<RefundDTO>> getRefundByCashier(@PathVariable Long cashierId) throws Exception {
        List<RefundDTO> refund = refundService.getRefundByCashier(cashierId);
        return  ResponseEntity.ok(refund);
    }

    @GetMapping("/branch/{branchId}")
    public ResponseEntity<List<RefundDTO>> getRefundByBranch(@PathVariable Long branchId) throws Exception {
        List<RefundDTO> refund = refundService.getRefundByBranch(branchId);
        return  ResponseEntity.ok(refund);
    }


}
