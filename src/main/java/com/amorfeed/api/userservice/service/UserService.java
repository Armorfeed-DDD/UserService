package com.amorfeed.api.userservice.service;

import com.amorfeed.api.userservice.entity.User;
import com.amorfeed.api.userservice.resource.RegisterResource;
import com.amorfeed.api.userservice.resource.UpdateResource;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    ResponseEntity<?> register(RegisterResource request);

    List<User> getAll();

    User update(Long userID, User request);
}
