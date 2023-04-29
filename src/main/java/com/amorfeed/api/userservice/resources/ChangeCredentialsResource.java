package com.amorfeed.api.userservice.resources;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ChangeCredentialsResource {

    @NotNull(message="Email must not be null")
    @NotBlank(message = "Email must not be blank")
    @NotEmpty(message="Email must not be empty")
    private String email;
    @NotNull(message="Password must not be null")
    @NotBlank(message = "Password must not be blank")
    @NotEmpty(message="Password must not be empty")
    private String password;
}
