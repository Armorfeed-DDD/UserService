package com.amorfeed.api.userservice.controller;


import com.amorfeed.api.userservice.mapping.UserMapper;
import com.amorfeed.api.userservice.resource.RegisterResource;
import com.amorfeed.api.userservice.resource.UserResource;
import com.amorfeed.api.userservice.service.UserService;
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


@Tag(name="Users", description = "Create, read, update and delete users")
@OpenAPIDefinition(info = @Info(title = "armorddd API", version = "2.0", description = "users Information"))
@SecurityScheme(name = "javainuseapi", scheme = "basic", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
@CrossOrigin
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> registerUser( @RequestBody RegisterResource request){
        return userService.register(request);
    }
    @PostMapping("/login")
    public ResponseEntity<?> LoginUser( @RequestBody RegisterResource request){
        return userService.register(request);
    }
    @GetMapping
    public Page<UserResource> getAllUsers(Pageable pageable) {
        return userMapper.modelListPage(userService.getAll(), pageable);
    }

}
