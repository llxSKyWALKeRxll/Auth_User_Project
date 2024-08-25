package com.v1.authuser.util;

import com.v1.authuser.dto.CreateUserDto;
import com.v1.authuser.dto.ResponseDto;
import com.v1.authuser.dto.UserDto;
import com.v1.authuser.entity.UserEntity;
import org.springframework.http.HttpStatus;

/**
 * Contains utility methods (mapper methods, conversion methods, etc.).
 *
 * @author Vansh Pratap Singh
 */
public class MapperUtil {

    /**
     * Fills the given response dto with the given values.
     *
     * @param responseDto           Response object.
     * @param success               Success.
     * @param message               Message.
     * @param httpStatus            Status response code.
     * @return                      Response object.
     * @param <T>                   Data type.
     */
    public static <T> ResponseDto<T> fillResponseDto(
            ResponseDto<T> responseDto,
            boolean success,
            String message,
            HttpStatus httpStatus,
            T data
    ) {

        if (responseDto == null) {
            responseDto = new ResponseDto<>();
        }

        responseDto.setSuccess(success);
        responseDto.setMessage(message);
        responseDto.setHttpStatus(httpStatus);
        responseDto.setData(data);

        return responseDto;

    }

    /**
     * Map the register user request to a user entity object.
     *
     * @param createUserDto             Create user request object.
     * @param encodedPassword           Encoded password.
     * @return                          Response object.
     */
    public static UserEntity registerUserEntity(
            CreateUserDto createUserDto,
            String encodedPassword
    ) {

        if (createUserDto == null) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setUsername(createUserDto.getUsername());
        userEntity.setEmail(createUserDto.getEmail());
        userEntity.setPassword(encodedPassword);

        return userEntity;

    }

    /**
     * Map the UserEntity object to a UserDto object.
     *
     * @param userEntity                UserEntity source object.
     * @return                          Response object.
     */
    public static UserDto mapUserDtoFromEntity(
            UserEntity userEntity
    ) {

        if (userEntity == null) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId(userEntity.getId());
        userDto.setEmail(userEntity.getEmail());
        userDto.setUsername(userEntity.getUsername());

        return userDto;

    }

}
