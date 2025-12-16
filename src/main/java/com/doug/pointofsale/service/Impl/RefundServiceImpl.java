package com.doug.pointofsale.service.Impl;

import com.doug.pointofsale.models.Refund;
import com.doug.pointofsale.payload.dto.RefundDTO;
import com.doug.pointofsale.service.RefundService;

import java.time.LocalDateTime;
import java.util.List;

public class RefundServiceImpl implements RefundService {
    @Override
    public RefundDTO createRefund(Refund refund) throws Exception {
        return null;
    }

    @Override
    public List<RefundDTO> getAllRefund() throws Exception {
        return List.of();
    }

    @Override
    public RefundDTO getRefundByCashId(String cashId) throws Exception {
        return null;
    }

    @Override
    public RefundDTO getRefundByShiftReport(Long shiftReportId) throws Exception {
        return null;
    }

    @Override
    public List<RefundDTO> getRefundByCashierAndDateRange(String cashierId, LocalDateTime startDate, LocalDateTime endDate) throws Exception {
        return List.of();
    }

    @Override
    public List<RefundDTO> getRefundByBranch(Long branchId) throws Exception {
        return List.of();
    }

    @Override
    public RefundDTO getRefundById(Long refundId) throws Exception {
        return null;
    }

    @Override
    public void deleteRefund(Long refundId) throws Exception {

    }
}
