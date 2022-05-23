package com.back.end.notifier.security;

import com.back.end.notifier.dto.ExceptionStatusBodyResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;

@Component
@Slf4j
public class AuthJWTEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    @Autowired
    public AuthJWTEntryPoint(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }


    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.error("Unauthorized message: {}", authException.getMessage());

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        final HttpStatus unauthorizedStatus = HttpStatus.UNAUTHORIZED;
        response.setStatus(unauthorizedStatus.value());

        ExceptionStatusBodyResponse responseDTO = new ExceptionStatusBodyResponse("User unauthorized", unauthorizedStatus.value(), unauthorizedStatus.getReasonPhrase(), Instant.now());
        objectMapper.writeValue(response.getOutputStream(), responseDTO);
    }
}
