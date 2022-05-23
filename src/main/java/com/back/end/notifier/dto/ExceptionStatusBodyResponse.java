package com.back.end.notifier.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionStatusBodyResponse {

    private String message;
    private int status;
    private String reasonPhrase;
    private Instant instant;
}
