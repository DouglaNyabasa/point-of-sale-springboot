package com.doug.pointofsale.mapper;

import com.doug.pointofsale.models.User;
import com.doug.pointofsale.payload.dto.UserDTO;

public class UserMapper {


    public static UserDTO toDTO(User savedUser) {
        UserDTO userDto = new UserDTO();
        userDto.setId(savedUser.getId());
        userDto.setFullName(savedUser.getFullName());
        userDto.setEmail(savedUser.getEmail());
        userDto.setRole(savedUser.getRole());
        userDto.setCreatedAt(savedUser.getCreatedAt());
        userDto.setPhoneNumber(String.valueOf(savedUser.getPhoneNumber()));
        userDto.setCreatedAt(savedUser.getCreatedAt());
        return userDto;
    }
}
