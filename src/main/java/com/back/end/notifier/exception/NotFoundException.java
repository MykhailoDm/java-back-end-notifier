package com.back.end.notifier.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends MasterStatusCodeException {

    public NotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
