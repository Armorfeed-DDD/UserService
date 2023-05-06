package com.amorfeed.api.userservice.service.impl;

import com.amorfeed.api.userservice.entity.Enum.Roles;
import com.amorfeed.api.userservice.entity.Role;
import com.amorfeed.api.userservice.repository.RoleRepository;
import com.amorfeed.api.userservice.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class RoleImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    private static final String[] DEFAULT_ROLES={ "ROLE_USER", "ROLE_OWNER", "ROLE_MUSICIAN"};

    @Override
    public void seed(){
        Arrays.stream(DEFAULT_ROLES).forEach(name->{
            Roles roleName=Roles.valueOf(name);
            if(!roleRepository.existsByName(roleName)){
                roleRepository.save((new Role()).withName(roleName));
            }
        });
    }

    @Override
    public List<Role> getAll(){
        return roleRepository.findAll();
    }
}
