package com.doug.pointofsale.service;

import com.doug.pointofsale.models.Refund;
import com.doug.pointofsale.payload.dto.RefundDTO;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface RefundService {

    RefundDTO createRefund(Refund refund) throws Exception;
    List<RefundDTO> getAllRefund() throws Exception;
    RefundDTO getRefundByCashId(String cashId) throws Exception;
    RefundDTO getRefundByShiftReport(Long shiftReportId) throws Exception;
    List<RefundDTO> getRefundByCashierAndDateRange(String cashierId, LocalDateTime startDate, LocalDateTime endDate) throws Exception;
    List<RefundDTO> getRefundByBranch(Long branchId) throws Exception;
}
