package com.example.auth.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDto {

    private int id;

    private String email;

    private String pwd;

    private String role;
}

