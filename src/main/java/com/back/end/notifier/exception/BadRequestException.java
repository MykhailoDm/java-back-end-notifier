package com.back.end.notifier.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends MasterStatusCodeException {
    public BadRequestException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
