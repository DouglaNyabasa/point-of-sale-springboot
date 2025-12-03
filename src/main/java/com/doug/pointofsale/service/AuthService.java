package com.doug.pointofsale.service;

import com.doug.pointofsale.models.User;
import com.doug.pointofsale.payload.dto.UserDto;
import com.doug.pointofsale.payload.response.AuthResponse;

public interface AuthService {

    AuthResponse login(String username, String password);

    AuthResponse signUp(UserDto userDto);

}
