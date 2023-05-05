package com.amorfeed.api.userservice.controller;


import com.amorfeed.api.userservice.entity.User;
import com.amorfeed.api.userservice.mapping.UserMapper;
import com.amorfeed.api.userservice.repository.UserRepository;
import com.amorfeed.api.userservice.resource.RegisterResource;
import com.amorfeed.api.userservice.resource.UpdateResource;
import com.amorfeed.api.userservice.resource.UserResource;
import com.amorfeed.api.userservice.service.UserService;
import com.amorfeed.api.userservice.shared.exception.ResourceNotFoundException;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;


@Tag(name="Users", description = "Create, read, update and delete users")
@OpenAPIDefinition(info = @Info(title = "armorddd API", version = "2.0", description = "users Information"))
@SecurityScheme(name = "javainuseapi", scheme = "basic", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
@CrossOrigin
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public UserController(UserService userService, UserMapper userMapper,
                          UserRepository userRepository) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> registerUser( @RequestBody RegisterResource request){
        return userService.register(request);
    }
    @GetMapping
    public Page<UserResource> getAllUsers(Pageable pageable) {
        return userMapper.modelListPage(userService.getAll(), pageable);
    }

    @PutMapping("{id}")
    public User updateUser(@PathVariable("id") Long id,
            @RequestBody User request){
        return userService.update(id, request);
    }

}
