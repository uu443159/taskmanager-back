package com.stefanini.taskmanager.security;

import lombok.Data;

@Data
public class AuthResponse {
    private String token;
    private String userName;

    public AuthResponse(String token, String userName) {
        this.token = token;
        this.userName = userName;
    }
}
