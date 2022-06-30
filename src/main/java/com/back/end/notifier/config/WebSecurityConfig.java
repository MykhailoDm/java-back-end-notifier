package com.back.end.notifier.config;

import com.back.end.notifier.security.AuthJWTEntryPoint;
import com.back.end.notifier.security.AuthJWTTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class WebSecurityConfig {

    private final UserDetailsService userDetailsService;
    private final AuthJWTEntryPoint authJWTEntryPoint;
    private final AuthJWTTokenFilter authJWTTokenFilter;

    @Autowired
    public WebSecurityConfig(UserDetailsService userDetailsService, AuthJWTEntryPoint authJWTEntryPoint, AuthJWTTokenFilter authJWTTokenFilter) {
        this.userDetailsService = userDetailsService;
        this.authJWTEntryPoint = authJWTEntryPoint;
        this.authJWTTokenFilter = authJWTTokenFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors()
                .and()
                .csrf().disable()
                .exceptionHandling().authenticationEntryPoint(authJWTEntryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests().antMatchers("/api/v1/auth/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .userDetailsService(userDetailsService)
                .addFilterBefore(authJWTTokenFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
