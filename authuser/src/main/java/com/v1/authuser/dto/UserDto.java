package com.v1.authuser.dto;

/**
 * DTO representing the user entity.
 *
 * @author Vansh Pratap Singh
 */
public class UserDto {

    private Long id;

    private String email;

    private String username;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
