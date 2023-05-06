package com.amorfeed.api.userservice.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResource {

    private long id;
    private String name;
    private String email;
    private List<RoleResource> roles;
}
