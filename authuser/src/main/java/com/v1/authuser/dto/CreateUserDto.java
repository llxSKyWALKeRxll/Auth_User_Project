package com.v1.authuser.dto;

/**
 * DTO representing the create/register user dto.
 *
 * @author Vansh Pratap Singh
 */
public class CreateUserDto extends LoginUserDto {

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
