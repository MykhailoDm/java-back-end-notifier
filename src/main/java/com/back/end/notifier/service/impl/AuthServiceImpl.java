package com.back.end.notifier.service.impl;

import com.back.end.notifier.dto.auth.LoginRequest;
import com.back.end.notifier.dto.auth.LoginResponse;
import com.back.end.notifier.dto.auth.SignupRequest;
import com.back.end.notifier.dto.auth.SignupResponse;
import com.back.end.notifier.entity.Role;
import com.back.end.notifier.entity.User;
import com.back.end.notifier.enums.UserRole;
import com.back.end.notifier.exception.InvalidPasswordException;
import com.back.end.notifier.exception.UserAlreadyExistsException;
import com.back.end.notifier.exception.UserNotFoundException;
import com.back.end.notifier.repo.RoleRepository;
import com.back.end.notifier.repo.UserRepository;
import com.back.end.notifier.service.AuthService;
import com.back.end.notifier.service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final JWTService jwtService;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, JWTService jwtService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public SignupResponse signup(SignupRequest signupRequest) {
        if (userRepository.existsByEmail(signupRequest.getEmail()) || userRepository.existsByUsername(signupRequest.getUsername())) {
            throw new UserAlreadyExistsException("User with such email or username already exists.");
        }

        User user = createUserBySignupRequest(signupRequest);
        userRepository.save(user);
        return new SignupResponse(user.getUsername(), user.getEmail());
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        Optional<User> userOptional = Optional.empty();
        if (userRepository.existsByUsername(loginRequest.getUsername())) {
            userOptional = userRepository.findByUsername(loginRequest.getUsername());
        } else if (userRepository.existsByEmail(loginRequest.getEmail())) {
            userOptional = userRepository.findByEmail(loginRequest.getEmail());
        }
        User user = userOptional.orElseThrow(() -> new UserNotFoundException("Invalid username or email."));

        if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            return new LoginResponse(jwtService.generateJWTToken(user.getUsername()));
        }
        throw new InvalidPasswordException("Incorrect password.");
    }

    private User createUserBySignupRequest(SignupRequest signupRequest) {
        Role role = roleRepository.findByUserRole(UserRole.ROLE_USER);
        return new User(null, signupRequest.getUsername(), passwordEncoder.encode(signupRequest.getPassword()), signupRequest.getEmail(), Set.of(role), Collections.emptyList());
    }
}
