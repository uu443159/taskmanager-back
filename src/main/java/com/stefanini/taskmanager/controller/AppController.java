package com.stefanini.taskmanager.controller;

import com.stefanini.taskmanager.model.User;
import com.stefanini.taskmanager.model.UserRole;
import com.stefanini.taskmanager.security.AuthRequest;
import com.stefanini.taskmanager.security.RegistrationRequest;
import com.stefanini.taskmanager.security.UserDetailsServiceImpl;
import com.stefanini.taskmanager.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class AppController {

    private UserDetailsService userDetailsService;
    private UserService userService;

    public AppController(UserDetailsService userDetailsService, UserService userService) {
        this.userDetailsService = userDetailsService;
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegistrationRequest registrationRequest) {

        try {

            userService.addUser(registrationRequest);

            return ResponseEntity.status(HttpStatus.CREATED).body(((UserDetailsServiceImpl) userDetailsService).login(new AuthRequest(registrationRequest.getUserName(), registrationRequest.getPassword())));
        } catch (UnexpectedRollbackException ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("This user name already exists");
        }
    }

    @PostMapping("/auth")
    public ResponseEntity<?> auth(@RequestBody AuthRequest request) {
        System.out.println("auth");
        try {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(((UserDetailsServiceImpl) userDetailsService).login(request));
        } catch (AuthenticationException ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid user name or password");
        }
    }

}
