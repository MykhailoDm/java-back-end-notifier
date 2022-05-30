package com.back.end.notifier.controller.advice;

import com.back.end.notifier.dto.ExceptionStatusBodyResponse;
import com.back.end.notifier.exception.MasterStatusCodeException;
import com.back.end.notifier.exception.UserAlreadyExistsException;
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

    private ResponseEntity<ExceptionStatusBodyResponse> handleStatusCodeExceptions(MasterStatusCodeException e) {
        final HttpStatus status = e.getHttpStatus();
        return ResponseEntity.status(status)
                .body(new ExceptionStatusBodyResponse(e.getMessage(), status));
    }
}
