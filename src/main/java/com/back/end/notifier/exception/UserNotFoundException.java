package com.back.end.notifier.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends MasterStatusCodeException {
    public UserNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
