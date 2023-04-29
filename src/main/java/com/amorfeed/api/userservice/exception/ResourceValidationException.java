package com.amorfeed.api.userservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ResourceValidationException  extends RuntimeException{
    public ResourceValidationException(){
    }

    public ResourceValidationException(String message){
        super(message);
    }

    public ResourceValidationException(String message, Throwable cause){
        super(message, cause);
    }


    public ResourceValidationException(String resourceName, String message){
        super(String.format("Not all constraints satisfied for %s: %s", resourceName, message));
    }


}
