package com.v1.authuser.service;

import com.v1.authuser.dto.CreateUserDto;
import com.v1.authuser.dto.LoginUserDto;
import com.v1.authuser.dto.ResponseDto;
import com.v1.authuser.exception.InvalidRequestException;

/**
 * Contains methods for auth user, such as sign up user, login user, etc. <br>
 * Includes jwt authentication.
 *
 * @author Vansh Pratap Singh
 */
public interface AuthUserService {

    /**
     * Add/register a user.
     *
     * @param createUserDto             Request dto.
     * @return                          Response object.
     */
    ResponseDto<?> createUser(
            CreateUserDto createUserDto
    ) throws InvalidRequestException;

    /**
     * Remove a user.
     *
     * @param id                        User id.
     * @return                          Response object.
     */
    ResponseDto<?> removeUser(
            Long id
    );

    /**
     * Login a user, returns a jwt token for the same.
     *
     * @param loginUserDto              Request dto.
     * @return                          Response object.
     */
    ResponseDto<?> loginUser(
            LoginUserDto loginUserDto
    ) throws InvalidRequestException;

}
