package com.v1.authuser.controller;

import com.v1.authuser.dto.CreateUserDto;
import com.v1.authuser.dto.LoginUserDto;
import com.v1.authuser.dto.ResponseDto;
import com.v1.authuser.exception.InvalidRequestException;
import com.v1.authuser.service.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Entry point for APIs, contains user related APIs.
 *
 * @author Vansh Pratap Singh
 */
@RestController
@RequestMapping(value = "/user/v1")
public class AuthUserController {

    @Autowired
    private AuthUserService authUserService;

    /**
     * API to create/register a user.
     *
     * @param createUserDto                     Request object.
     * @return                                  Response object.
     * @throws InvalidRequestException          In case of validation failures.
     */
    @PostMapping()
    public ResponseEntity<?> createUser(
            @Valid @RequestBody CreateUserDto createUserDto
    ) throws InvalidRequestException {

        ResponseDto<?> response = authUserService.createUser(createUserDto);
        return new ResponseEntity<>(response, response.getHttpStatus());

    }

    /**
     * API to login a user.
     *
     * @param loginUserDto                      Request object.
     * @return                                  Response object.
     * @throws InvalidRequestException          In case of validation failures.
     */
    @PostMapping(value = "/login")
    public ResponseEntity<?> loginUser(
            @Valid @RequestBody LoginUserDto loginUserDto
    ) throws InvalidRequestException {

        ResponseDto<?> response = authUserService.loginUser(loginUserDto);
        return new ResponseEntity<>(response, response.getHttpStatus());

    }


}
