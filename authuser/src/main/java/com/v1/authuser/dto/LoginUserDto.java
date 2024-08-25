package com.v1.authuser.dto;

import javax.validation.constraints.Email;

public class LoginUserDto {

    @Email(message = "Invalid email.")
    private String email;

    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
