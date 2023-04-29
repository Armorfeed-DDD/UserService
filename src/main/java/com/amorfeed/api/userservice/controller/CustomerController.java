package com.amorfeed.api.userservice.controller;

import com.amorfeed.api.userservice.entity.Customer;
import com.amorfeed.api.userservice.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    private final CustomerService customerService;
    public CustomerController(CustomerService customerService){
        this.customerService=customerService;
    }

    @PutMapping("/{email}/change-email")
    public ResponseEntity<Customer> changeEmail(@PathVariable String email,
                                                  @NotBlank @RequestParam String newEmail){
        Customer customer=customerService.updateEmail(email,newEmail);
        if(customer!=null){
            return new ResponseEntity<>(customer, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{email}/change-password")
    public ResponseEntity<Customer> changePassword(@PathVariable String email,
                                                     @NotBlank @RequestParam String currentPassword,
                                                     @NotBlank @RequestParam String newPassword){
        Customer customer=customerService.updatePassword(email,currentPassword,newPassword);
        if(customer!=null){
            return new ResponseEntity<>(customer, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
