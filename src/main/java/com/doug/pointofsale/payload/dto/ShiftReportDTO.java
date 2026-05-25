package com.doug.pointofsale.payload.dto;

import com.doug.pointofsale.models.*;

import java.time.LocalDateTime;
import java.util.List;

public class ShiftReportDTO {

    private Long id;

    private LocalDateTime shiftStart;
    private LocalDateTime shiftEnd;
    private Double totalSales;
    private Double totalRefunds;
    private Double netSales;
    private int totalOrders;

    private UserDTO cashier;
    private Long cashierId;
    private Long branchId;
    private BranchDTO branch;


    private List<PaymentSummary> paymentSummaries;

    private List<ProductDTO> topSellingProducts;

    private List<OrderDto> recentOrders;
    private List<RefundDTO> refunds;

    public Long getCashierId() {
        return cashierId;
    }

    public void setCashierId(Long cashierId) {
        this.cashierId = cashierId;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getShiftStart() {
        return shiftStart;
    }

    public void setShiftStart(LocalDateTime shiftStart) {
        this.shiftStart = shiftStart;
    }

    public LocalDateTime getShiftEnd() {
        return shiftEnd;
    }

    public void setShiftEnd(LocalDateTime shiftEnd) {
        this.shiftEnd = shiftEnd;
    }

    public Double getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(Double totalSales) {
        this.totalSales = totalSales;
    }

    public Double getTotalRefunds() {
        return totalRefunds;
    }

    public void setTotalRefunds(Double totalRefunds) {
        this.totalRefunds = totalRefunds;
    }

    public Double getNetSales() {
        return netSales;
    }

    public void setNetSales(Double netSales) {
        this.netSales = netSales;
    }

    public int getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(int totalOrders) {
        this.totalOrders = totalOrders;
    }

    public UserDTO getCashier() {
        return cashier;
    }

    public void setCashier(UserDTO cashier) {
        this.cashier = cashier;
    }

    public BranchDTO getBranch() {
        return branch;
    }

    public void setBranch(BranchDTO branch) {
        this.branch = branch;
    }

    public List<PaymentSummary> getPaymentSummaries() {
        return paymentSummaries;
    }

    public void setPaymentSummaries(List<PaymentSummary> paymentSummaries) {
        this.paymentSummaries = paymentSummaries;
    }

    public List<ProductDTO> getTopSellingProducts() {
        return topSellingProducts;
    }

    public void setTopSellingProducts(List<ProductDTO> topSellingProducts) {
        this.topSellingProducts = topSellingProducts;
    }

    public List<OrderDto> getRecentOrders() {
        return recentOrders;
    }

    public void setRecentOrders(List<OrderDto> recentOrders) {
        this.recentOrders = recentOrders;
    }

    public List<RefundDTO> getRefunds() {
        return refunds;
    }

    public void setRefunds(List<RefundDTO> refunds) {
        this.refunds = refunds;
    }
}
