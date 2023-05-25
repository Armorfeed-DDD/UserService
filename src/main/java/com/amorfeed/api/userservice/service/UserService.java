package com.amorfeed.api.userservice.service;

import com.amorfeed.api.userservice.comunication.AuthTokenResponse;
import com.amorfeed.api.userservice.comunication.AuthenticateRequest;
import com.amorfeed.api.userservice.comunication.RegisterRequest;
import com.amorfeed.api.userservice.entity.User;
import com.amorfeed.api.userservice.resource.ChangeEmailResource;
import com.amorfeed.api.userservice.resource.ChangePasswordResource;
import com.amorfeed.api.userservice.resource.AuthenticateResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
public interface UserService extends UserDetailsService {

    ResponseEntity<?> authenticate( AuthenticateRequest request);
    ResponseEntity<?> register(RegisterRequest request);
    ResponseEntity<?> changeEmail(ChangeEmailResource request);
    ResponseEntity<?> changePassword(ChangePasswordResource request);
    AuthTokenResponse validateToken(String token);

    User enableUser(String email);

    String confirmToken(String token);

    List<User> getAll();
    User getById(Long id);
}
