package com.stefanini.taskmanager.security;

import com.stefanini.taskmanager.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class UserDetailsImpl implements UserDetails {

    private long id;
    private String userName;
    private String password;
    private Collection<? extends GrantedAuthority> grantedAuthorities;

    public UserDetailsImpl(long id, String userName, String password, Collection<? extends GrantedAuthority> grantedAuthorities) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.grantedAuthorities = grantedAuthorities;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
