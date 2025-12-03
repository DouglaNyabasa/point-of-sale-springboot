package com.doug.pointofsale.payload.dto;

import com.doug.pointofsale.domain.UserRole;
import lombok.Data;


import java.time.LocalDate;

@Data
public class UserDto {


    private Long id;
    private String fullName;
    private String email;
    private UserRole role;
    private String password;
    private String phoneNumber;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private LocalDate lastLoginAt;
}
