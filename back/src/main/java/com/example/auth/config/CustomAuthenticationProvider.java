package com.example.auth.config;

import com.example.auth.dao.CustomerRepo;
import com.example.auth.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private CustomerRepo customerRepo;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public CustomAuthenticationProvider(CustomerRepo customerRepo, PasswordEncoder passwordEncoder) {
        this.customerRepo = customerRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        Customer customer = customerRepo.findByEmail(authentication.getName()).orElseThrow(() -> new BadCredentialsException("Invalid email"));

        if (passwordEncoder.matches(authentication.getCredentials().toString(), customer.getPwd())) {
            List<GrantedAuthority> authorities = new ArrayList<>();

            authorities.add(new SimpleGrantedAuthority("ROLE_" + customer.getRole()));
            return new UsernamePasswordAuthenticationToken(
                    authentication.getName(), authentication.getCredentials().toString(), authorities);
        } else {
            throw new BadCredentialsException("Invalid password");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
