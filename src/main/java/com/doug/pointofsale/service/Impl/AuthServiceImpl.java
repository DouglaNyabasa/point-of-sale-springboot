package com.doug.pointofsale.service.Impl;

import com.doug.pointofsale.Exception.UserException;
import com.doug.pointofsale.configaration.JwtProvider;
import com.doug.pointofsale.domain.UserRole;
import com.doug.pointofsale.mapper.UserMapper;
import com.doug.pointofsale.models.User;
import com.doug.pointofsale.payload.dto.UserDto;
import com.doug.pointofsale.payload.response.AuthResponse;
import com.doug.pointofsale.repository.UserRepository;
import com.doug.pointofsale.service.AuthService;
import com.doug.pointofsale.service.CustomUserImplementation;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;

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
    public AuthResponse login(UserDto userDto) throws UserException {
        String email = userDto.getEmail();
        String password = userDto.getPassword();

        Authentication authentication = authenticate(email,password);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String role = authorities.iterator().next().getAuthority();
        String jwt = jwtProvider.generateToken(authentication);
        User user = userRepository.findByEmail(email);
        user.setLastLoginAt(LocalDate.now());
        userRepository.save(user);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("User Login Successfully !!!");
        authResponse.setUser(UserMapper.toDTO(user));

        return authResponse;
    }

    private Authentication authenticate(String email, String password) throws UserException {
        UserDetails userDetails = customUserImplementation.loadUserByUsername(email);
        if (userDetails == null) {
            throw new UserException("email id doesn't exist"+ email);
        }
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new UserException("password doesn't match");
        }


        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    @Override
    public AuthResponse signUp(UserDto userDto) throws UserException {
        User user = userRepository.findByEmail(userDto.getEmail());
        if (user != null) {
            throw new UserException("email id is already registered !!!");
        }
        if (userDto.getRole().equals(UserRole.ROLE_ADMIN)) {
            throw new UserException("role admin is not allowed !!!");

        }
        User newUser = new User();
        newUser.setEmail(userDto.getEmail());
        newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        newUser.setFullName(userDto.getFullName());
        newUser.setRole(userDto.getRole());
        newUser.setPhoneNumber(userDto.getPhoneNumber());
        newUser.setLastLoginAt(LocalDate.now());
        newUser.setCreatedAt(LocalDate.now());
        User savedUser = userRepository.save(newUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateToken(authentication);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("User Successfully Registered!!!");
        authResponse.setUser(UserMapper.toDTO(savedUser));


        return authResponse;
    }
}
