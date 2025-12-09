package com.doug.pointofsale.payload.response;

import com.doug.pointofsale.payload.dto.UserDTO;


public class AuthResponse {
    private String jwt;
    private String message;
    private UserDTO user;


    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
