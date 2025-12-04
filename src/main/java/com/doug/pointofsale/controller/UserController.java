package com.doug.pointofsale.controller;

import com.doug.pointofsale.Exception.UserException;
import com.doug.pointofsale.mapper.UserMapper;
import com.doug.pointofsale.models.User;
import com.doug.pointofsale.payload.dto.UserDto;
import com.doug.pointofsale.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")

public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/profile")
    public ResponseEntity<UserDto> getUserProfile(@RequestHeader("Authorization") String jwt) throws UserException {
      User user = userService.getUserFromJwtToken(jwt);
      return ResponseEntity.ok(UserMapper.toDTO(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@RequestHeader("Authorization") String jwt,@PathVariable("id") Long id ) throws UserException, Exception {
        User user = userService.getUserById(id);

        return ResponseEntity.ok(UserMapper.toDTO(user));
    }
}
