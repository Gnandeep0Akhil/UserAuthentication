package com.example.userauthservice.UserDTOs;

import lombok.Data;

@Data
public class UserLoginDTO {
    private String email;
    private String password;
}
