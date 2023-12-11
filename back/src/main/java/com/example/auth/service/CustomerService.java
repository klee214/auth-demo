package com.example.auth.service;

import com.example.auth.dao.CustomerRepo;
import com.example.auth.dto.CustomerDto;
import com.example.auth.entity.Customer;
import com.example.auth.exception.ConflictsException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    //    private UserDetailsManager jdbcUserDetailsManager;
    private CustomerRepo customerRepo;
    private PasswordEncoder passwordEncoder;

//    @Autowired
//    public CustomerService(CustomerRepo customerRepo, UserDetailsManager jdbcUserDetailsManager, PasswordEncoder passwordEncoder) {
//        this.customerRepo = customerRepo;
//        this.jdbcUserDetailsManager = jdbcUserDetailsManager;
//        this.passwordEncoder = passwordEncoder;
//    }

    @Autowired
    public CustomerService(CustomerRepo customerRepo, PasswordEncoder passwordEncoder) {
        this.customerRepo = customerRepo;
        this.passwordEncoder = passwordEncoder;
    }


    public Customer dtoToEntity(CustomerDto customerDto) {
        Customer customer = new Customer();

        customer.setEmail(customerDto.getEmail());
        customer.setPwd(this.passwordEncoder.encode(customerDto.getPwd()));
        customer.setRole(customerDto.getRole());

        return customer;
    }

//    @Transactional
//    public boolean registerCustomer(CustomerDto customerDto) {
//        Customer customer = customerRepo.findByEmail(customerDto.getEmail()).orElseGet(() -> {
//            List<GrantedAuthority> authorities = new ArrayList<>();
//            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
//
//            jdbcUserDetailsManager.createUser(new User(customerDto.getEmail(), passwordEncoder.encode(customerDto.getPwd()), authorities));
//            return null;
//        });
//
//        if (customer == null) {
//
//            customerRepo.save(this.dtoToEntity(customerDto));
//
//            return true;
//        }
//        throw new ConflictsException("customer", "email", customerDto.getEmail());
//    }

    @Transactional
    public boolean registerCustomer(CustomerDto customerDto) {
        Customer customer = customerRepo.findByEmail(customerDto.getEmail()).orElseGet(() -> {
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(customerDto.getRole()));
            customerRepo.save(this.dtoToEntity(customerDto));

            return null;
        });

        if (customer == null) {
            return true;
        }
        throw new ConflictsException("customer", "email", customerDto.getEmail());
    }
}
