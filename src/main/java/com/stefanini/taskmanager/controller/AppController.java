package com.stefanini.taskmanager.controller;

import com.stefanini.taskmanager.model.User;
import com.stefanini.taskmanager.model.UserRole;
import com.stefanini.taskmanager.security.*;
import com.stefanini.taskmanager.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class AppController {

    private final UserDetailsService userDetailsService;
    private final UserService userService;

    public AppController(UserDetailsService userDetailsService, UserService userService) {
        this.userDetailsService = userDetailsService;
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegistrationRequest registrationRequest) {

        try {
            User user = new User();
            UserRole userRole = new UserRole();

            user.setPassword(registrationRequest.getPassword());
            user.setUserName(registrationRequest.getUserName());
            user.setFirstName(registrationRequest.getFirstName());
            user.setLastName(registrationRequest.getLastName());

            userRole.setRoleName(registrationRequest.getRoleName());
            user.setUserRole(userRole);

            userService.addUser(user);

            return ResponseEntity.status(HttpStatus.CREATED).body(((UserDetailsServiceImpl) userDetailsService).login(new AuthRequest(registrationRequest.getUserName(), registrationRequest.getPassword())));
        } catch (UnexpectedRollbackException ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("This user name already exists");
        }
    }

    @PostMapping("/auth")
    public ResponseEntity<?> auth(@RequestBody AuthRequest request) {
        try {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(((UserDetailsServiceImpl) userDetailsService).login(request));
        } catch (AuthenticationException ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid user name or password");
        }
    }

}
