package com.amorfeed.api.userservice.comunication;


import com.amorfeed.api.userservice.resource.UserResource;

public class RegisterResponse extends BaseResponse<UserResource> {
    public RegisterResponse(String message){
        super(message);
    }

    public RegisterResponse(UserResource resource){
        super(resource);
    }
}
