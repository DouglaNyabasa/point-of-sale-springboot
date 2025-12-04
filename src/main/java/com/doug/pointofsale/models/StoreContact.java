package com.doug.pointofsale.models;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Email;

@Embeddable
public class StoreContact {

    private String address;
    private String phone;
    @Email(message = "Invalid email format !!!")
    private String email;




    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
