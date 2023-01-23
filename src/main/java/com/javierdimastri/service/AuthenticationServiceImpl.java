package com.javierdimastri.service;

import com.javierdimastri.model.Role;
import com.javierdimastri.model.User;
import com.javierdimastri.payload.request.SignupRequest;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;
    @Autowired
    PasswordEncoder encoder;

    public void registerUser(SignupRequest signUpRequest) {
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> payloadRoles = signUpRequest.getRoles();
        List<Role> roles = roleService.assignRolesByName(payloadRoles);

        user.setRoles(roles);
        userService.storedUser(user);
    }
}
