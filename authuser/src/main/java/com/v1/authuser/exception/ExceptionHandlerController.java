package com.v1.authuser.exception;

import com.v1.authuser.dto.ExceptionResponseDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<?> handleInvalidRequestException(InvalidRequestException ex) {

        ExceptionResponseDto<Map<String, Object>> exceptionResponseDto = new ExceptionResponseDto<>();

        Map<String, Object> detailsMap = new LinkedHashMap<>();

        detailsMap.put("cause", "Bad request.");
        detailsMap.put("details", "Failed validation(s).");

        List<String> errorMessageList = new ArrayList<>();

        detailsMap.put("errorList", errorMessageList);

        String message = ex.getMessage();

        if (!StringUtils.isEmpty(message)) {

            for (String currMessage: message.split("\n")) {

                if (StringUtils.isEmpty(currMessage)) {
                    continue;
                }

                errorMessageList.add(currMessage);

            }

        }

        exceptionResponseDto.setSuccess(false);
        exceptionResponseDto.setDetails(detailsMap);

        return new ResponseEntity<>(exceptionResponseDto, HttpStatus.BAD_REQUEST);

    }

}
