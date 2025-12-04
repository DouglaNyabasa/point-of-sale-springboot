package com.doug.pointofsale.controller;

import com.doug.pointofsale.Exception.UserException;
import com.doug.pointofsale.payload.dto.UserDto;
import com.doug.pointofsale.payload.response.AuthResponse;
import com.doug.pointofsale.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> loginHandler(@RequestBody UserDto userDto) throws UserException {
        return  ResponseEntity.ok(
                authService.login(userDto)
        );
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signupHandler(@RequestBody UserDto userDto) throws UserException {
        return  ResponseEntity.ok(
                authService.signUp(userDto)
        );
    }
}
