package com.amorfeed.api.userservice.controller;


import com.amorfeed.api.userservice.comunication.AuthTokenResponse;
import com.amorfeed.api.userservice.comunication.AuthenticateRequest;
import com.amorfeed.api.userservice.comunication.RegisterRequest;
import com.amorfeed.api.userservice.mapping.UserMapper;
import com.amorfeed.api.userservice.resource.ChangeEmailResource;
import com.amorfeed.api.userservice.resource.ChangePasswordResource;
import com.amorfeed.api.userservice.resource.UserResource;
import com.amorfeed.api.userservice.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@SecurityRequirement(name = "armorddd")
@Tag(name="Users", description = "Create, read, update and delete users")
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping("/auth/sign-in")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody AuthenticateRequest request){
        return userService.authenticate(request);
    }

    @PostMapping("/auth/sign-up")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest request){
        return userService.register(request);
    }
    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('CUSTOMER') or hasRole('ENTERPRISE')")
    public ResponseEntity<?> getAllUsers(Pageable pageable){
        Page<UserResource> resources=userMapper.modelListPage(userService.getAll(), pageable);
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/confirm")
    public String confirmAccount(@RequestParam("token") String token) {
        return this.userService.confirmToken(token);
    }

    @PutMapping("/change-email")
    public ResponseEntity<?> changeEmail(@RequestBody ChangeEmailResource request){
        return userService.changeEmail(request);
    }

    @PutMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordResource request){
        return userService.changePassword(request);
    }
    @GetMapping("{userId}")
    public UserResource getUserById(@PathVariable Long userId){
        return userMapper.  toResource(userService.getById(userId));
    }
    @GetMapping("/auth/validate-token/{token}")
    public AuthTokenResponse validateToken(@PathVariable("token") String token) {
        return userService.validateToken(token);
    }
    @GetMapping("/auth/validate-enterprise/{enterpriseId}")
    public boolean validateEnterpriseId(@PathVariable("enterpriseId") Long enterpriseId) {
        return userService.validateEnterpriseId(enterpriseId);
    }
    @GetMapping("/auth/validate-customer/{customerId}")
    public boolean validateCustomerId(@PathVariable("customerId") Long customerId) {
        return userService.validateCustomerId(customerId);
    }
}
