package com.amorfeed.api.userservice.service.impl;

import com.amorfeed.api.userservice.service.ConfirmationTokenService;
import com.amorfeed.api.userservice.service.UserService;
import com.amorfeed.api.userservice.comunication.RegisterResponse;
import com.amorfeed.api.userservice.entity.ConfirmationToken;
import com.amorfeed.api.userservice.comunication.AuthTokenResponse;
import com.amorfeed.api.userservice.comunication.AuthenticateRequest;
import com.amorfeed.api.userservice.comunication.AuthenticateResponse;
import com.amorfeed.api.userservice.comunication.RegisterRequest;
import com.amorfeed.api.userservice.comunication.RegisterResponse;
import com.amorfeed.api.userservice.config.JwtHandler;
import com.amorfeed.api.userservice.config.UserDetailsImpl;
import com.amorfeed.api.userservice.entity.Enum.Roles;
import com.amorfeed.api.userservice.entity.Role;
import com.amorfeed.api.userservice.entity.User;
import com.amorfeed.api.userservice.repository.RoleRepository;
import com.amorfeed.api.userservice.repository.UserRepository;
import com.amorfeed.api.userservice.resource.ChangeEmailResource;
import com.amorfeed.api.userservice.resource.ChangePasswordResource;
import com.amorfeed.api.userservice.resource.SavedUserResource;
import com.amorfeed.api.userservice.resource.AuthenticateResource;
import com.amorfeed.api.userservice.resource.UserResource;
import com.amorfeed.api.userservice.service.UserService;
import com.amorfeed.api.userservice.shared.mapping.EnhancedModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserImpl implements UserService {
    private static final Logger logger= LoggerFactory.getLogger(UserImpl.class);

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtHandler handler;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private EnhancedModelMapper mapper;
    @Autowired
    private ConfirmationTokenService confirmationTokenService;

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

        if(encoder.matches(request.getCurrentPassword(),user.get().getPassword())){
            user.get().setPassword(encoder.encode(request.getNewPassword()));
            userRepository.save(user.get());
            UserResource resource=mapper.map(user,UserResource.class);
            RegisterResponse response=new RegisterResponse(resource);
            return ResponseEntity.ok(response.getResource());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

    }

    public ResponseEntity<?> authenticate(AuthenticateRequest request){
        Optional<User> userFoundByName = this.userRepository.findByEmail(request.getUsername());
        if(userFoundByName.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found while trying to find by email");
        }
        if(userFoundByName.get().getEnabled() == false) {
            return ResponseEntity.badRequest().body("User is not enabled yet");
        }
        try{
            Authentication authentication=authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(),
                            request.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token= handler.generateToken(authentication);
            UserDetailsImpl userDetails=(UserDetailsImpl)  authentication.getPrincipal();
            List<String> roles=userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());
            AuthenticateResource resource=mapper.map(userDetails, AuthenticateResource.class);
            resource.setToken(token);
            resource.setRoles(roles);
            resource.setName(userDetails.getUsername());
            AuthenticateResponse response=new AuthenticateResponse(resource);
            return ResponseEntity.ok(response.getResource());
        } catch (Exception e){
            AuthenticateResponse response=new AuthenticateResponse(
                    String.format("An error occurred while authenticating: %s", e.getMessage()));
            return ResponseEntity.badRequest().body(response.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> register(RegisterRequest request){
        if (userRepository.existsByName(request.getName())){
            AuthenticateResponse response= new AuthenticateResponse("Username is already used.");
            return ResponseEntity.badRequest().body(response.getMessage());
        }
        if (userRepository.existsByEmail(request.getEmail())){
            AuthenticateResponse response=new AuthenticateResponse("Email is already used.");
            return ResponseEntity.badRequest().body(response.getMessage());
        }
        try {
            Set<String> rolesStringSet=request.getRoles();
            Set<Role> roles = new HashSet<>();
            if (rolesStringSet==null){
                roleRepository.findByName(Roles.ADMIN)
                        .map(roles::add)
                        .orElseThrow(()->new RuntimeException("Role not found."));
            } else {
                rolesStringSet.forEach(roleString->
                        roleRepository.findByName(Roles.valueOf(roleString))
                                .map(roles::add)
                                .orElseThrow(()->new RuntimeException("Role not found.")));
            }
            logger.info("Roles: {}", roles);

            User user=new User()
                    .withName(request.getName())
                    .withEmail(request.getEmail())
                    .withPassword(encoder.encode(request.getPassword()))
                    .withEnabled(false)
                    .withRoles(roles);
            User newUser = userRepository.save(user);
            UserResource resource= mapper.map(user, UserResource.class);
            RegisterResponse response= new RegisterResponse(resource);

            String token = UUID.randomUUID().toString();

            ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                newUser
            );
    
            confirmationTokenService.saveConfirmationToken(confirmationToken);
    
            return ResponseEntity.ok(new SavedUserResource("User successfully registered", token));
        } catch (Exception e){
            RegisterResponse response= new RegisterResponse(e.getMessage());
            return ResponseEntity.badRequest().body(response.getMessage());
        }
    }
    @Override
    public List<User> getAll(){
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

    public User getById(Long id) {
        return userRepository.getById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        User user= userRepository.findByEmail(username)
                .orElseThrow(()->new UsernameNotFoundException(String.format("User not found with username: %s", username)));
        return UserDetailsImpl.build(user);
    }

    @Override
    public AuthTokenResponse validateToken(String token) {
        boolean isValidToken = handler.validateToken(token);
        String message = handler.getValidationMessage();
        handler.setValidationMessage("");
        return new AuthTokenResponse(isValidToken, message);
    }
}
