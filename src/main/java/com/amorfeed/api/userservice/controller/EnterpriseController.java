package com.amorfeed.api.userservice.controller;

import com.amorfeed.api.userservice.entity.Enterprise;
import com.amorfeed.api.userservice.entity.User;
import com.amorfeed.api.userservice.mapping.EnterpriseMapper;
import com.amorfeed.api.userservice.repository.EnterpriseRepository;
import com.amorfeed.api.userservice.service.EnterpriseService;
import com.amorfeed.api.userservice.shared.exception.ResourceNotFoundException;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name="Enterprise", description = "Create, read, update and delete enterprise")
@OpenAPIDefinition(info = @Info(title = "armorddd API", version = "2.0", description = "enterprise Information"))
@SecurityScheme(name = "javainuseapi", scheme = "basic", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
@CrossOrigin
@RestController
@RequestMapping("/api/v1/enterprise")
public class EnterpriseController {
    private final EnterpriseRepository enterpriseRepository;

   //private final EnterpriseMapper enterpriseMapper;

    private final EnterpriseService enterpriseService;

    public EnterpriseController(EnterpriseRepository enterpriseRepository, EnterpriseService enterpriseService) {
        this.enterpriseRepository = enterpriseRepository;
        //this.enterpriseMapper = enterpriseMapper;
        this.enterpriseService = enterpriseService;
    }

    @PutMapping("{id}")
    public Optional<Enterprise> updateEnterprise(@PathVariable("id") Long id,
                                                 @RequestBody Enterprise request){
        return enterpriseService.update(id, request);
    }

    @GetMapping("/{score}")
    public List<Enterprise> getListEnterpriseByScore(@PathVariable("score") int score){
        return enterpriseService.getListbyScore(score);
    }

}
