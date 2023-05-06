package com.amorfeed.api.userservice.service;

import com.amorfeed.api.userservice.entity.User;
import com.amorfeed.api.userservice.resource.ChangeEmailResource;
import com.amorfeed.api.userservice.resource.ChangePasswordResource;
import com.amorfeed.api.userservice.resource.RegisterResource;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    ResponseEntity<?> register(RegisterResource request);
    ResponseEntity<?> changeEmail(ChangeEmailResource request);
    ResponseEntity<?> changePassword(ChangePasswordResource request);
    List<User> getAll();

    User enableUser(String email);

    String confirmToken(String token);
}
