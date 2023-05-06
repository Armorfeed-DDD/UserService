package com.amorfeed.api.userservice.comunication;

import com.amorfeed.api.userservice.resource.AuthenticateResource;

public class AuthenticateResponse extends BaseResponse<AuthenticateResource> {
    public AuthenticateResponse(String message) {
        super(message);
    }

    public AuthenticateResponse(AuthenticateResource resource) {
        super(resource);
    }
}
