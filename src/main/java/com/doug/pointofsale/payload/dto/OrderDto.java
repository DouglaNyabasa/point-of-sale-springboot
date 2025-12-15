package com.doug.pointofsale.payload.dto;

import com.doug.pointofsale.domain.PaymentType;
import com.doug.pointofsale.models.Branch;
import com.doug.pointofsale.models.Customer;
import com.doug.pointofsale.models.OrderItem;
import com.doug.pointofsale.models.User;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.time.LocalDateTime;
import java.util.List;

public class OrderDto {

    private Long id;

    private Double totalAmount;
    private LocalDateTime createdAt;

    private BranchDTO branch;
    private Long branchId;

    private UserDTO cashier;

    private Long customerId;

    private PaymentType paymentType;
    private Customer customer;
    private List<OrderItem> items;

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public BranchDTO getBranch() {
        return branch;
    }

    public void setBranch(BranchDTO branch) {
        this.branch = branch;
    }

    public UserDTO getCashier() {
        return cashier;
    }

    public void setCashier(UserDTO cashier) {
        this.cashier = cashier;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
