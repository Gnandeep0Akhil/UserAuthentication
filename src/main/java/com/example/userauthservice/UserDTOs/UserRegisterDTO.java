package com.example.userauthservice.UserDTOs;

import lombok.Data;

import java.util.List;

@Data
public class UserRegisterDTO {
    String firstName;
    String lastName;
    String email;
    String password;
    List<String> roles;
}
