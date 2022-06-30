package com.back.end.notifier.controller.advice;

import com.back.end.notifier.dto.ExceptionStatusBodyResponse;
import com.back.end.notifier.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ExceptionStatusBodyResponse> userAlreadyExistsException(UserAlreadyExistsException e) {
        return handleStatusCodeExceptions(e);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionStatusBodyResponse> userNotFoundException(UserNotFoundException e) {
        return handleStatusCodeExceptions(e);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionStatusBodyResponse> notFoundException(NotFoundException e) {
        return handleStatusCodeExceptions(e);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<ExceptionStatusBodyResponse> invalidPasswordException(InvalidPasswordException e) {
        return handleStatusCodeExceptions(e);
    }

    private ResponseEntity<ExceptionStatusBodyResponse> handleStatusCodeExceptions(MasterStatusCodeException e) {
        final HttpStatus status = e.getHttpStatus();
        return ResponseEntity.status(status)
                .body(new ExceptionStatusBodyResponse(e.getMessage(), status));
    }
}
