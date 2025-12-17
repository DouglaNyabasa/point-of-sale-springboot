package com.doug.pointofsale.mapper;

import com.doug.pointofsale.models.Order;
import com.doug.pointofsale.models.Product;
import com.doug.pointofsale.models.Refund;
import com.doug.pointofsale.models.ShiftReport;
import com.doug.pointofsale.payload.dto.OrderDto;
import com.doug.pointofsale.payload.dto.ProductDTO;
import com.doug.pointofsale.payload.dto.RefundDTO;
import com.doug.pointofsale.payload.dto.ShiftReportDTO;

import java.util.List;
import java.util.stream.Collectors;

public class ShiftReportMapper {

    public static ShiftReportDTO toDTO(ShiftReport entity) {
        ShiftReportDTO shiftReportDTO = new ShiftReportDTO();
        shiftReportDTO.setId(entity.getId());
        shiftReportDTO.setShiftStart(entity.getShiftStart());
        shiftReportDTO.setShiftEnd(entity.getShiftEnd());
        shiftReportDTO.setTotalOrders(entity.getTotalOrders());
        shiftReportDTO.setTotalSales(entity.getTotalSales());
        shiftReportDTO.setTotalRefunds(entity.getTotalRefunds());
        shiftReportDTO.setNetSales(entity.getNetSales());
        shiftReportDTO.setCashier(UserMapper.toDTO(entity.getCashier()));
        shiftReportDTO.setCashierId(entity.getCashier().getId());
        shiftReportDTO.setBranchId(entity.getBranch().getId());
        shiftReportDTO.setRecentOrders(mapOrders(entity.getRecentOrders()));
        shiftReportDTO.setTopSellingProducts(mapProducts(entity.getTopSellingProducts()));
        shiftReportDTO.setRefunds(mapRefund(entity.getRefunds()));
        shiftReportDTO.setPaymentSummaries(entity.getPaymentSummaries());
        return shiftReportDTO;

    }

    private static List<RefundDTO> mapRefund(List<Refund> refunds) {
        if (refunds == null || refunds.isEmpty()) {
            return null;

        }
        return refunds.stream().map(RefundMapper::toDTO).collect(Collectors.toList());

    }

    private static List<ProductDTO> mapProducts(List<Product> topSellingProducts) {
        if (topSellingProducts == null || topSellingProducts.isEmpty()) {
            return null;
        }
        return topSellingProducts.stream().map(ProductMapper::toDTO).collect(Collectors.toList());
    }

    private static List<OrderDto> mapOrders(List<Order> recentOrders) {
        if (recentOrders == null || recentOrders.isEmpty()) {
            return null;
        }
        return recentOrders.stream().map(OrderMapper::toDTO).collect(Collectors.toList());
    }


}
