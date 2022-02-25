package com.stefanini.taskmanager.security;

import com.stefanini.taskmanager.model.User;
import com.stefanini.taskmanager.model.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public final class UserDetailsFactory {

    public UserDetailsFactory() {
    }

    public static UserDetailsImpl create(User user) {
        List<UserRole> roles = new ArrayList<>();
        roles.add(user.getUserRole());

        return new UserDetailsImpl(user.getId(), user.getUserName(), user.getPassword(), mapToGrantedAuthorities(roles));
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<UserRole> userRoles) {
        return userRoles.stream()
                .map(role -> new
                SimpleGrantedAuthority(role.getRoleName()))
                .collect(Collectors.toList());
    }
}
