package com.back.end.notifier.exception;

import org.springframework.http.HttpStatus;

public class InvalidPasswordException extends MasterStatusCodeException {
    public InvalidPasswordException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
