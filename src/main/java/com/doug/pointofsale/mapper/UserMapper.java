package com.doug.pointofsale.mapper;

import com.doug.pointofsale.models.User;
import com.doug.pointofsale.payload.dto.UserDto;

public class UserMapper {


    public static UserDto toDTO(User savedUser) {
        UserDto userDto = new UserDto();
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
