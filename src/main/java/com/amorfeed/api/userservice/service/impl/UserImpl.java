package com.amorfeed.api.userservice.service.impl;

import com.amorfeed.api.userservice.service.UserService;
import com.amorfeed.api.userservice.comunication.RegisterResponse;
import com.amorfeed.api.userservice.entity.User;
import com.amorfeed.api.userservice.repository.UserRepository;
import com.amorfeed.api.userservice.resource.RegisterResource;
import com.amorfeed.api.userservice.resource.UserResource;
import com.amorfeed.api.userservice.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EnhancedModelMapper mapper;

    @Override
    public ResponseEntity<?> register(RegisterResource request) {
        User user=new User()
                .withName(request.getName())
                .withEmail(request.getEmail())
                .withPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        UserResource resource= mapper.map(user, UserResource.class);
        RegisterResponse response= new RegisterResponse(resource);
        return ResponseEntity.ok(response.getResource());
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

}
