package com.amorfeed.api.userservice.controller;

import com.amorfeed.api.userservice.entity.Enterprise;
import com.amorfeed.api.userservice.service.EnterpriseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/api/enterprise")
public class EnterpriseController {
    private final EnterpriseService enterpriseService;
    public EnterpriseController(EnterpriseService enterpriseService){
        this.enterpriseService=enterpriseService;
    }



    @PutMapping("/{email}/change-password")
    public ResponseEntity<Enterprise> changePassword(@PathVariable String email,
                                                     @NotBlank @RequestParam String currentPassword,
                                                     @NotBlank @RequestParam String newPassword){
        Enterprise enterprise=enterpriseService.updatePassword(email,currentPassword,newPassword);
        if(enterprise!=null){
            return new ResponseEntity<>(enterprise, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{email}/change-email")
    public ResponseEntity<Enterprise> changeEmail(@PathVariable String email,
                                                  @NotBlank @RequestParam String newEmail){
        Enterprise enterprise=enterpriseService.updateEmail(email,newEmail);
        if(enterprise!=null){
            return new ResponseEntity<>(enterprise, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}

