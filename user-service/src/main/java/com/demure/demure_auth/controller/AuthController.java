package com.demure.demure_auth.controller;

import com.demure.demure_auth.auth.TokenUtil;
import com.demure.demure_auth.dto.UserDto;
import com.demure.demure_auth.service.LogoutService;
import com.demure.demure_auth.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final LogoutService logoutService;
    private final TokenUtil tokenUtil;

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

    @PostMapping("/logout")
    public ResponseEntity<String> logoutUser(HttpServletRequest request) {
        String token = getTokenFromRequest(request);

        if (token == null) {
            return ResponseEntity.badRequest().body("Token is missing");
        }

        long expirationTime = tokenUtil.getExpirationTimeFromToken(token);
        logoutService.blacklistToken(token, expirationTime);

        return ResponseEntity.ok("Successfully logged out");
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}

