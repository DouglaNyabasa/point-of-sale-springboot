package com.doug.pointofsale.service;

import com.doug.pointofsale.Exception.UserException;
import com.doug.pointofsale.models.User;
import com.doug.pointofsale.payload.dto.UserDto;
import com.doug.pointofsale.payload.response.AuthResponse;

public interface AuthService {

    AuthResponse login(UserDto userDto) throws UserException;

    AuthResponse signUp(UserDto userDto) throws UserException;

}
