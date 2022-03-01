package com.stefanini.taskmanager.security;

import com.stefanini.taskmanager.model.User;
import com.stefanini.taskmanager.service.UserService;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Primary
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserService userService;
    private AuthenticationManager authenticationManager;
    private JwtProvider jwtProvider;

//    public UserDetailsServiceImpl(UserService userService, AuthenticationManager authenticationManager, JwtProvider jwtProvider) {
//        this.userService = userService;
//        this.authenticationManager = authenticationManager;
//        this.jwtProvider = jwtProvider;
//    }

    @Override
    public UserDetailsImpl loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.showUserByName(username);

        return UserDetailsFactory.create(user);
    }

    public AuthResponse login(AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getLogin(), authRequest.getPassword()));

        return jwtProvider.generateToken(authentication);
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public AuthenticationManager getAuthenticationManager() {
        return authenticationManager;
    }

    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public JwtProvider getJwtProvider() {
        return jwtProvider;
    }

    public void setJwtProvider(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }
}
