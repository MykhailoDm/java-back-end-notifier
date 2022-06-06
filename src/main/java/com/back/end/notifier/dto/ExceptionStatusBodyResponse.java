package com.back.end.notifier.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionStatusBodyResponse {

    @JsonProperty("message")
    private String message;
    @JsonProperty("status")
    private int status;
    @JsonProperty("reasonPhrase")
    private String reasonPhrase;
    @JsonProperty("instant")
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
