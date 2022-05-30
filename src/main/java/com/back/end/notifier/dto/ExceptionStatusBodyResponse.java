package com.back.end.notifier.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionStatusBodyResponse {

    private String message;
    private int status;
    private String reasonPhrase;
    private Instant instant;

    public ExceptionStatusBodyResponse(String message, HttpStatus httpStatus) {
        this(message, httpStatus.value(), httpStatus.getReasonPhrase());
    }

    public ExceptionStatusBodyResponse(String message, int status, String reasonPhrase) {
        this.message = message;
        this.status = status;
        this.reasonPhrase = reasonPhrase;
        this.instant = Instant.now();
    }
}
