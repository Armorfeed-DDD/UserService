package com.amorfeed.api.userservice.service;

import com.amorfeed.api.userservice.entity.User;
import com.amorfeed.api.userservice.resource.RegisterResource;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    ResponseEntity<?> register(RegisterResource request);

    List<User> getAll();

    User enableUser(String email);

    String confirmToken(String token);
}
