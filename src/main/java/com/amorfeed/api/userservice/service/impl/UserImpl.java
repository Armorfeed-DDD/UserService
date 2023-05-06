package com.amorfeed.api.userservice.service.impl;

import com.amorfeed.api.userservice.resource.UpdateResource;
import com.amorfeed.api.userservice.service.UserService;
import com.amorfeed.api.userservice.comunication.RegisterResponse;
import com.amorfeed.api.userservice.entity.User;
import com.amorfeed.api.userservice.repository.UserRepository;
import com.amorfeed.api.userservice.resource.RegisterResource;
import com.amorfeed.api.userservice.resource.UserResource;
import com.amorfeed.api.userservice.shared.exception.ResourceNotFoundException;
import com.amorfeed.api.userservice.shared.exception.ResourceValidationException;
import com.amorfeed.api.userservice.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;


@Service
public class UserImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EnhancedModelMapper mapper;

    private final Validator validator;
    private static final String ENTITY = "User";

    public UserImpl(Validator validator) {
        this.validator = validator;
    }

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

    @Override
    public User update(Long id, User request) {
        Set<ConstraintViolation<User>> violations = validator.validate(request);
        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return userRepository.findById(id).map(existingUser ->
                userRepository.save(
                        existingUser.withName(request.getName())
                                .withEmail(request.getEmail())
                                .withPassword(passwordEncoder.encode(request.getPassword()))
                )).orElseThrow(() -> new ResourceNotFoundException(ENTITY, id));
    }

    @Override
    public boolean existsById(Long id) { return userRepository.existsById(id); }

}
