package com.doug.pointofsale.service.Impl;

import com.doug.pointofsale.configaration.JwtProvider;
import com.doug.pointofsale.models.User;
import com.doug.pointofsale.payload.dto.UserDto;
import com.doug.pointofsale.payload.response.AuthResponse;
import com.doug.pointofsale.repository.UserRepository;
import com.doug.pointofsale.service.AuthService;
import com.doug.pointofsale.service.CustomUserImplementation;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final CustomUserImplementation customUserImplementation;


    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtProvider jwtProvider, CustomUserImplementation customUserImplementation) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
        this.customUserImplementation = customUserImplementation;
    }

    @Override
    public AuthResponse login(String username, String password) {
        return null;
    }

    @Override
    public AuthResponse signUp(UserDto userDto) {
        User user = userRepository.findByEmail(userDto.getEmail());
        if (user != null) {
            throw new Exception("email id is already registered !!!");
        }
        return null;
    }
}
