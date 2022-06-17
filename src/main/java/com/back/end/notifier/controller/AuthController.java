package com.back.end.notifier.controller;

import com.back.end.notifier.dto.auth.LoginRequest;
import com.back.end.notifier.dto.auth.LoginResponse;
import com.back.end.notifier.dto.auth.SignupRequest;
import com.back.end.notifier.dto.auth.SignupResponse;
import com.back.end.notifier.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signin")
    public LoginResponse authenticateUser(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping("/signup")
    public SignupResponse registerUser(@RequestBody SignupRequest signUpRequest) {
        return authService.signup(signUpRequest);
    }
}
