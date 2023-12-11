package com.example.auth.controller;

import com.example.auth.dto.CustomerDto;
import com.example.auth.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(value = "/register")
    public ResponseEntity<String> registerNewCustomer(@RequestBody CustomerDto customerDto) {
        boolean success = customerService.registerCustomer(customerDto);

        if (success) {
            return new ResponseEntity<>("created", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("something wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "/customers")
    public ResponseEntity<String> getCustomers(Authentication authentication) {
        System.out.println(authentication.toString());
        return new ResponseEntity<>("customers", HttpStatus.OK);
    }

    @PostMapping(value = "/customers")
    public ResponseEntity<String> updateCustomers(Authentication authentication) {
        System.out.println(authentication.toString());
        return new ResponseEntity<>("customers updated", HttpStatus.OK);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<String> loginTest(Authentication authentication) {
        System.out.println(authentication);
        return new ResponseEntity<>("login", HttpStatus.OK);
    }
}
