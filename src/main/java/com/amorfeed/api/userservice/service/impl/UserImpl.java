package com.amorfeed.api.userservice.service.impl;

import com.amorfeed.api.userservice.service.ConfirmationTokenService;
import com.amorfeed.api.userservice.service.UserService;
import com.amorfeed.api.userservice.comunication.RegisterResponse;
import com.amorfeed.api.userservice.entity.ConfirmationToken;
import com.amorfeed.api.userservice.entity.User;
import com.amorfeed.api.userservice.repository.UserRepository;
import com.amorfeed.api.userservice.resource.RegisterResource;
import com.amorfeed.api.userservice.resource.SavedUserResource;
import com.amorfeed.api.userservice.resource.UserResource;
import com.amorfeed.api.userservice.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EnhancedModelMapper mapper;
    @Autowired
    private ConfirmationTokenService confirmationTokenService;

    @Override
    public ResponseEntity<?> register(RegisterResource request) {
        User user=new User()
                .withName(request.getName())
                .withEmail(request.getEmail())
                .withPassword(passwordEncoder.encode(request.getPassword()))
                .withEnabled(false);
        User newUser = userRepository.save(user);
        //UserResource resource= mapper.map(user, UserResource.class);
        //RegisterResponse response= new RegisterResponse(resource);

        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
            token,
            LocalDateTime.now(),
            LocalDateTime.now().plusMinutes(15),
            newUser
        );

        confirmationTokenService.saveConfirmationToken(
                confirmationToken);

        return ResponseEntity.ok(new SavedUserResource("User successfully registered", token));
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User enableUser(String email) {
        Optional<User> user = this.userRepository.findByEmail(email);
        if(user.isEmpty()){
            throw new NotFoundException("User not found while trying to enable user");
        }
        if(user.get().getEnabled()) {
            throw new IllegalStateException("User already enabled");
        }
        user.get().setEnabled(true);
        return this.userRepository.save(user.get());
    }

    @Override
    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = this.confirmationTokenService.getByToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        this.confirmationTokenService.setConfirmedAt(token);
        enableUser(confirmationToken.getUser().getEmail());
        return "confirmed";
    }

}
