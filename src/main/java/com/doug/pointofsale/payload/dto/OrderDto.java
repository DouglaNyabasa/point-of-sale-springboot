package com.doug.pointofsale.payload.dto;

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

    private UserDTO cashier;

    private Customer customer;

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
}
