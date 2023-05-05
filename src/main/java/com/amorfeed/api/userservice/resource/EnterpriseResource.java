package com.amorfeed.api.userservice.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnterpriseResource {

    private int id;
    private byte[] image;
    private String ruc;
    private String phone;
    private String description;
}
