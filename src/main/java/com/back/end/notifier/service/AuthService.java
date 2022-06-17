package com.back.end.notifier.service;

import com.back.end.notifier.dto.auth.LoginRequest;
import com.back.end.notifier.dto.auth.LoginResponse;
import com.back.end.notifier.dto.auth.SignupRequest;
import com.back.end.notifier.dto.auth.SignupResponse;

public interface AuthService {

    SignupResponse signup(SignupRequest signupRequest);

    LoginResponse login(LoginRequest loginRequest);
}
