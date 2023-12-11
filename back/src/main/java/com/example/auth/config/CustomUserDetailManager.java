package com.example.auth.config;

import com.example.auth.dao.CustomerRepo;
import com.example.auth.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
//
//
//@Service
//public class CustomUserDetailManager extends JdbcUserDetailsManager {
//
//    private CustomerRepo customerRepo;
//
//    private PasswordEncoder passwordEncoder;
//
//    @Autowired
//    public CustomUserDetailManager(DataSource dataSource, CustomerRepo customerRepo, PasswordEncoder passwordEncoder) {
//        super(dataSource);
//        this.customerRepo = customerRepo;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        System.out.println("custom one : " + email);
//        Customer customer = customerRepo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
//
//        System.out.println("custom one : " + customer.getPwd());
//
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        authorities.add(new SimpleGrantedAuthority(customer.getRole()));
//
//        User user = new User(customer.getEmail(),customer.getPwd(), authorities);
//
//        System.out.println(user);
//
//        return user;
//    }
//
//}
