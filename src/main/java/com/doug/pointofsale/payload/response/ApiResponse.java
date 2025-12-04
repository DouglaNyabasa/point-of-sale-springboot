package com.doug.pointofsale.payload.response;

import lombok.Data;


public class ApiResponse {
    String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
