package com.amorfeed.api.userservice.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UpdateEnterpriseResource {
    private int id;

    private byte[] image;

    @NotNull
    @NotBlank
    @Size(max=12)
    private String ruc;

    @NotNull
    @NotBlank
    @Size(max=10)
    private String phone;

    @NotNull
    private String description;
}
