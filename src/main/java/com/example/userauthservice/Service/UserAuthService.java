package com.example.userauthservice.Service;

import com.example.userauthservice.Models.Role;
import com.example.userauthservice.Models.User;
import com.example.userauthservice.Repository.RoleRepository;
import com.example.userauthservice.Repository.UserRepository;
import com.example.userauthservice.UserDTOs.LoginResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserAuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    public Boolean registerUser(String firstName, String lastName, String email, String password, List<String> roles) {
        List<Role> finalRoles = new ArrayList<>();
        for (String role : roles) {
            Role roleObj = roleRepository.findByRoleName(role);
            if (roleObj != null) {
                finalRoles.add(roleObj);
            } else {
                Role tempRole = new Role();
                tempRole.setRoleName(role);
                finalRoles.add(tempRole);
            }
        }
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setRoles(finalRoles);

        User savedUser = userRepository.save(user);
        return passwordEncoder.matches(password, savedUser.getPassword());
    }

    public Boolean loginUser(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );

        return authentication.isAuthenticated();
    }
}
