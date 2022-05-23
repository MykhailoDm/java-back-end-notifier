package com.back.end.notifier.exception;

import org.springframework.http.HttpStatus;

public class UsernameNotFoundException extends MasterStatusCodeException {
    public UsernameNotFoundException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
