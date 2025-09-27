package com.example.userauthservice.Controller;

import com.example.userauthservice.Service.UserAuthService;
import com.example.userauthservice.UserDTOs.LoginResponseDTO;
import com.example.userauthservice.UserDTOs.UserLoginDTO;
import com.example.userauthservice.UserDTOs.UserRegisterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserAuthController {

    @Autowired
    private UserAuthService userAuthService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRegisterDTO userRegisterDTO) {
        Boolean saved = userAuthService.registerUser(userRegisterDTO.getFirstName(), userRegisterDTO.getLastName(), userRegisterDTO.getEmail(), userRegisterDTO.getPassword(), userRegisterDTO.getRoles());
        if (saved) {
            return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("User not registered", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/login")
    public Boolean loginUser(@RequestBody UserLoginDTO userLoginDTO) {
        return userAuthService.loginUser(userLoginDTO.getEmail(), userLoginDTO.getPassword());
    }
}
