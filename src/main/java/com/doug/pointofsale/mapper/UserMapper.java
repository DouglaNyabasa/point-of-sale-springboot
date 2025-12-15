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
        userDto.setPhoneNumber(String.valueOf(savedUser.getPhoneNumber()));
        userDto.setBranchId(savedUser.getStore() != null ? savedUser.getBranch().getId(): null);
        userDto.setStoreId(savedUser.getBranch() != null ? savedUser.getStore().getId() : null);
        return userDto;
    }

    public static User toEntity(UserDTO userDTO) {
        User user = new User();
        user.setFullName(userDTO.getFullName());
        user.setEmail(userDTO.getEmail());
        user.setRole(userDTO.getRole());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setPassword(userDTO.getPassword());
        user.setCreatedAt(userDTO.getCreatedAt());
        user.setUpdatedAt(userDTO.getUpdatedAt());
        user.setLastLoginAt(userDTO.getLastLoginAt());
        return user;
    }
}
