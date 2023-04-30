package com.amorfeed.api.userservice.service.impl;

import com.amorfeed.api.userservice.comunication.RegisterResponse;
import com.amorfeed.api.userservice.entity.User;
import com.amorfeed.api.userservice.repository.UserRepository;
import com.amorfeed.api.userservice.resource.ChangeEmailResource;
import com.amorfeed.api.userservice.resource.ChangePasswordResource;
import com.amorfeed.api.userservice.resource.RegisterResource;
import com.amorfeed.api.userservice.resource.UserResource;
import com.amorfeed.api.userservice.service.UserService;
import com.amorfeed.api.userservice.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<?> changeEmail(ChangeEmailResource request){
        Optional<User> user=userRepository.findByEmail(request.getCurrentEmail());
        if(user.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        user.get().setEmail(request.getNewEmail());
        userRepository.save(user.get());
        UserResource resource=mapper.map(user,UserResource.class);
        RegisterResponse response= new RegisterResponse(resource);
        return ResponseEntity.ok(response.getResource());
    }

    @Override
    public ResponseEntity<?> changePassword(ChangePasswordResource request){
        Optional<User> user=userRepository.findByEmail(request.getEmail());
        if(user.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        if(passwordEncoder.matches(request.getCurrentPassword(),user.get().getPassword())){
            user.get().setPassword(passwordEncoder.encode(request.getNewPassword()));
            userRepository.save(user.get());
            UserResource resource=mapper.map(user,UserResource.class);
            RegisterResponse response=new RegisterResponse(resource);
            return ResponseEntity.ok(response.getResource());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

    }
    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

}
