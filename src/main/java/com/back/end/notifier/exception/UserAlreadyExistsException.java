package com.back.end.notifier.exception;

import org.springframework.http.HttpStatus;

public class UserAlreadyExistsException extends MasterStatusCodeException {
    public UserAlreadyExistsException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
