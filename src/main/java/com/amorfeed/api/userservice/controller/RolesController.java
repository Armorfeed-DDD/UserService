package com.amorfeed.api.userservice.controller;

import com.amorfeed.api.userservice.mapping.RoleMapper;
import com.amorfeed.api.userservice.resource.RoleResource;
import com.amorfeed.api.userservice.service.RoleService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SecurityRequirement(name = "armorddd")
@Tag(name = "Roles", description = "Create, read, update and delete roles")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/roles")
public class RolesController {

    private final RoleService roleService;

    private final RoleMapper mapper;

    public RolesController(RoleService roleService, RoleMapper mapper){
        this.roleService=roleService;
        this.mapper=mapper;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('CUSTOMER') or hasRole('ENTERPRISE')")
    public ResponseEntity<?> getAllRoles(Pageable pageable){
        Page<RoleResource> resources=mapper.modelListToPage(roleService.getAll(), pageable);
        return ResponseEntity.ok(resources);
    }
}
