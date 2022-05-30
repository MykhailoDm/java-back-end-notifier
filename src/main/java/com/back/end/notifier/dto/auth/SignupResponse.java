package com.back.end.notifier.dto.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupResponse {

    @JsonProperty("username")
    private String username;
    @JsonProperty("email")
    private String email;
}
