package com.back.end.notifier.security;

import com.back.end.notifier.service.JWTService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Slf4j
@Component
public class AuthJWTTokenFilter extends OncePerRequestFilter {

    private final JWTService jwtService;

    private final UserDetailsService userDetailsService;

    @Autowired
    public AuthJWTTokenFilter(JWTService jwtService, UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            Optional<String> tokenOp = getToken(request.getHeader(HttpHeaders.AUTHORIZATION));
            if (tokenOp.isPresent()) {
                String token = tokenOp.get();
                if (jwtService.isValidJWTToken(token)) {
                    String username = jwtService.getUsernameFromToken(token);
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                    var authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        } catch (Exception e) {
            log.error("Cannot retrieve authentication: {}", e.getMessage());
        }

        filterChain.doFilter(request, response);
    }

    private Optional<String> getToken(String bearerToken) {
        final int tokenIndex = 7;
        if (StringUtils.isEmpty(bearerToken) || bearerToken.length() <= tokenIndex) {
            return Optional.empty();
        } else {
            return Optional.of(bearerToken.substring(tokenIndex));
        }
    }
}
