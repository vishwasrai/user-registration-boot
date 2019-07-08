package com.fse.user.model;

import javax.validation.constraints.Size;

public class Login {

    @Size(min = 1, message = "Username is required")
    private String username;
    @Size(min = 1, message = "Password is required")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
