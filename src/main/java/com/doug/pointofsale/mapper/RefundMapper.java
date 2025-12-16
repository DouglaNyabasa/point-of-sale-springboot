package com.doug.pointofsale.mapper;

import com.doug.pointofsale.models.Refund;
import com.doug.pointofsale.payload.dto.RefundDTO;

public class RefundMapper {

    public static RefundDTO toDTO(Refund refund) {
        RefundDTO refundDTO = new RefundDTO();
        refundDTO.setId(refund.getId());
        refundDTO.setReason(refund.getReason());
        refundDTO.setAmount(refund.getAmount());
        refundDTO.setCashierName(refund.getCashier().getFullName());
        refundDTO.setBranchId(refund.getBranch().getId());
        refundDTO.setOrderId(refund.getOrder().getId());
        refundDTO.setShiftReport(refund.getShiftReport());
        refundDTO.setCreatedAt(refund.getShiftReport() != null ?  refund.getCreatedAt(): null);
        refundDTO.setPaymentType(refund.getPaymentType());

        return refundDTO;
    }
}
