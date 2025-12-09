package com.doug.pointofsale.service;

import com.doug.pointofsale.Exception.UserException;
import com.doug.pointofsale.payload.dto.UserDTO;
import com.doug.pointofsale.payload.response.AuthResponse;

public interface AuthService {

    AuthResponse login(UserDTO userDto) throws UserException;

    AuthResponse signUp(UserDTO userDto) throws UserException;

}
