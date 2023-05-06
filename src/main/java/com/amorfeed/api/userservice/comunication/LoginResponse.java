package com.amorfeed.api.userservice.comunication;

import com.amorfeed.api.userservice.resource.UserResource;
public class LoginResponse extends BaseResponse<UserResource> {
    public LoginResponse(String message){
        super(message);
    }

    public LoginResponse(UserResource resource){
        super(resource);
    }
}