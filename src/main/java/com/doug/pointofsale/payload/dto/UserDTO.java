package com.doug.pointofsale.payload.dto;

import com.doug.pointofsale.domain.UserRole;
import com.doug.pointofsale.models.Store;


import java.time.LocalDate;


public class UserDTO {


    private Long id;
    private String fullName;
    private String email;
    private UserRole role;
    private String password;
    private String phoneNumber;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private LocalDate lastLoginAt;
    private Store store;

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDate getLastLoginAt() {
        return lastLoginAt;
    }

    public void setLastLoginAt(LocalDate lastLoginAt) {
        this.lastLoginAt = lastLoginAt;
    }


}
