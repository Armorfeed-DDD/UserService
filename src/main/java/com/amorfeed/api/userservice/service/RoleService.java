package com.amorfeed.api.userservice.service;

import com.amorfeed.api.userservice.entity.Role;

import java.util.List;
public interface RoleService {
        void seed();
        List<Role> getAll();
}
