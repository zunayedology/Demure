package com.demure.demure_auth.controller;

import com.demure.demure_auth.dto.UserDto;
import com.demure.demure_auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto) {
        UserDto registeredUser = userService.registerUser(userDto);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<String> authenticate(@RequestBody UserDto userDto) {
        String token = userService.authenticate(userDto.getUsername(), userDto.getPassword());
        if (token == null) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(token);
        }
    }

    @GetMapping("/user")
    public ResponseEntity<UserDto> getCurrentUser() {
        UserDto currentUser = userService.getCurrentUser();
        return ResponseEntity.ok(currentUser);
    }
}
