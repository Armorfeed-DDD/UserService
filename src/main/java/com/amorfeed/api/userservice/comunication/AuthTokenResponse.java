package com.amorfeed.api.userservice.comunication;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthTokenResponse {
    private boolean isValidToken;
    private String message;
}
