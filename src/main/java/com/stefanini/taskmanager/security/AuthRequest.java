package com.stefanini.taskmanager.security;

import lombok.Data;

@Data
public class AuthRequest {
    private String login;
    private String password;

    public AuthRequest(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
