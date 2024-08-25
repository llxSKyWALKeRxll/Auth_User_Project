package com.v1.authuser.service.impl;

import com.v1.authuser.dto.CreateUserDto;
import com.v1.authuser.dto.LoginUserDto;
import com.v1.authuser.dto.ResponseDto;
import com.v1.authuser.dto.UserDto;
import com.v1.authuser.entity.UserEntity;
import com.v1.authuser.exception.InvalidRequestException;
import com.v1.authuser.repository.UserRepository;
import com.v1.authuser.service.AuthUserService;
import com.v1.authuser.service.JwtService;
import com.v1.authuser.util.MapperUtil;
import com.v1.authuser.util.PasswordUtils;
import com.v1.authuser.util.ValidatorUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Contains methods for auth user, such as sign up user, login user, etc. <br>
 * Includes jwt authentication.
 *
 * @author Vansh Pratap Singh
 */
@Service
public class AuthUserServiceImpl implements AuthUserService {

    private static final Logger logger = LoggerFactory.getLogger(AuthUserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    /**
     * Add/register a user.
     *
     * @param createUserDto             Request dto.
     * @return                          Response object.
     */
    @Override
    public ResponseDto<?> createUser(
            CreateUserDto createUserDto
    ) throws InvalidRequestException {

        ResponseDto<UserDto> responseDto = new ResponseDto<>();

        String failedValidationMessage = ValidatorUtil.validateRegisterUserDto(createUserDto);

        if (!StringUtils.isEmpty(failedValidationMessage)) {

            throw new InvalidRequestException(failedValidationMessage);

        }

        String encodedPassword = PasswordUtils.hashPassword(createUserDto.getPassword());

        UserEntity userEntity = MapperUtil.registerUserEntity(createUserDto, encodedPassword);

        try {

            userRepository.save(userEntity);

        } catch (Exception ex) {

            logger.error("*** Exception occurred while saving user entity ***\n" +
                    "Exception is => {}", ExceptionUtils.getStackTrace(ex));

            return MapperUtil.fillResponseDto(responseDto, false,
                    "Some error occurred.", HttpStatus.INTERNAL_SERVER_ERROR, null);

        }

        responseDto.setSuccess(true);
        responseDto.setMessage("User registration successful.");
        responseDto.setHttpStatus(HttpStatus.OK);
        responseDto.setData(MapperUtil.mapUserDtoFromEntity(userEntity));

        return responseDto;

    }

    /**
     * Remove a user.
     *
     * @param id                        User id.
     * @return                          Response object.
     */
    @Override
    public ResponseDto<?> removeUser(
            Long id
    ) {
        return null;
    }

    /**
     * Login a user, returns a jwt token for the same.
     *
     * @param loginUserDto              Request dto.
     * @return                          Response object.
     */
    @Override
    public ResponseDto<?> loginUser(
            LoginUserDto loginUserDto
    ) throws InvalidRequestException {

        ResponseDto<String> responseDto = new ResponseDto<>();

        String failedValidationMessage = ValidatorUtil.validateLoginUserDto(loginUserDto);

        if (!StringUtils.isEmpty(failedValidationMessage)) {

            throw new InvalidRequestException(failedValidationMessage);

        }

        UserEntity userEntity;

        try {

            Optional<UserEntity> byEmail = userRepository.findByEmail(loginUserDto.getEmail());

            if (byEmail.isEmpty()) {

                return MapperUtil.fillResponseDto(responseDto, false,
                        "No user exists for the given email.", HttpStatus.NOT_FOUND, null);

            } else {

                userEntity = byEmail.get();

            }

        } catch (Exception ex) {

            logger.error("*** Exception occurred while attempting to login user with email {} ***\n" +
                    "Exception is => {}", loginUserDto.getEmail(), ExceptionUtils.getStackTrace(ex));

            return MapperUtil.fillResponseDto(responseDto, false,
                    "Some error occurred.", HttpStatus.INTERNAL_SERVER_ERROR, null);

        }

        boolean matchPassword = PasswordUtils.matchPassword(userEntity.getPassword(), loginUserDto.getPassword());

        if (!matchPassword) {

            return MapperUtil.fillResponseDto(responseDto, false,
                    "Password does not match.", HttpStatus.UNAUTHORIZED, null);

        }

        String jwtToken = jwtService.createJwtToken(loginUserDto.getEmail(), userEntity.getUsername());

        if (StringUtils.isEmpty(jwtToken)) {

            return MapperUtil.fillResponseDto(responseDto, false,
                    "Some error occurred.", HttpStatus.INTERNAL_SERVER_ERROR, null);

        }

        responseDto.setSuccess(true);
        responseDto.setMessage("Login successful.");
        responseDto.setHttpStatus(HttpStatus.OK);
        responseDto.setData(jwtToken);

        return responseDto;

    }
}
