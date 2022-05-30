package com.back.end.notifier.controller;

import com.back.end.notifier.dto.auth.LoginRequest;
import com.back.end.notifier.dto.auth.LoginResponse;
import com.back.end.notifier.dto.auth.SignupRequest;
import com.back.end.notifier.dto.auth.SignupResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @PostMapping("/signin")
    public LoginResponse authenticateUser(@RequestBody LoginRequest loginRequest) {
        // TODO implement signin
        return new LoginResponse("", Instant.now());
    }

    @PostMapping("/signup")
    public SignupResponse registerUser(@RequestBody SignupRequest signUpRequest) {

        return new SignupResponse("", "");
    }
}
