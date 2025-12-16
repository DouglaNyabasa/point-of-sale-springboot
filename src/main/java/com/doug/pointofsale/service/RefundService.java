package com.doug.pointofsale.service;

import com.doug.pointofsale.models.Refund;
import com.doug.pointofsale.payload.dto.RefundDTO;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface RefundService {

    RefundDTO createRefund(RefundDTO refund) throws Exception;
    List<RefundDTO> getAllRefund() throws Exception;
    List<RefundDTO> getRefundByCashier(Long cashier) throws Exception;
    List<RefundDTO> getRefundByShiftReport(Long shiftReportId) throws Exception;
    List<RefundDTO> getRefundByCashierIdAndDateRange(Long cashierId, LocalDateTime startDate, LocalDateTime endDate) throws Exception;
    List<RefundDTO> getRefundByBranch(Long branchId) throws Exception;
    RefundDTO getRefundById(Long refundId) throws Exception;
    void deleteRefund(Long refundId) throws Exception;
}
