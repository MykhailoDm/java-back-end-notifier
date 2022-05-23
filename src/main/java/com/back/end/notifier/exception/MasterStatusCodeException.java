package com.back.end.notifier.exception;

import org.springframework.http.HttpStatus;

public class MasterStatusCodeException extends RuntimeException {
    private final HttpStatus httpStatus;

    public MasterStatusCodeException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
