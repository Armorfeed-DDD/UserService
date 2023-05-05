package com.amorfeed.api.userservice.resource;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateResource {
    private Long id;

    @NotNull
    @NotBlank
    @Size(max=60)
    private String name;

    @NotNull
    @NotBlank
    @Size(max=60)
    private String email;

    @NotNull
    @NotBlank
    @Size(max=60)
    private String password;
}
