package com.v1.authuser.util;

import com.v1.authuser.dto.CreateUserDto;
import com.v1.authuser.dto.LoginUserDto;
import org.apache.commons.lang3.StringUtils;

/**
 * Contains validator util methods.
 *
 * @author Vansh Pratap Singh
 */
public class ValidatorUtil {

    /**
     * Validate the given create user request dto.
     *
     * @param createUserDto             Request object.
     * @return                          Failed validation message.
     */
    public static String validateRegisterUserDto(
            CreateUserDto createUserDto
    ) {

        String failedValidationMessage = "";

        if (createUserDto ==  null) {
            failedValidationMessage += "Request body must not be null.\n";
            return failedValidationMessage;
        }

        if(StringUtils.isEmpty(createUserDto.getUsername())) {
            failedValidationMessage += "User name must not be empty.\n";
        }

        if (StringUtils.isEmpty(createUserDto.getEmail())) {
            failedValidationMessage += "Email must not be empty.\n";
        }

        if (StringUtils.isEmpty(createUserDto.getPassword())) {
            failedValidationMessage += "Password must not be empty.\n";
        }

        return failedValidationMessage;

    }

    /**
     * Validate the given login user request dto.
     *
     * @param loginUserDto              Request object.
     * @return                          Failed validation message.
     */
    public static String validateLoginUserDto(
            LoginUserDto loginUserDto
    ) {

        String failedValidationMessage = "";

        if (loginUserDto ==  null) {
            failedValidationMessage += "Request body must not be null.\n";
            return failedValidationMessage;
        }

        if (StringUtils.isEmpty(loginUserDto.getEmail())) {
            failedValidationMessage += "Email must not be empty.\n";
        }

        if (StringUtils.isEmpty(loginUserDto.getPassword())) {
            failedValidationMessage += "Password must not be empty.\n";
        }

        return failedValidationMessage;

    }

}
