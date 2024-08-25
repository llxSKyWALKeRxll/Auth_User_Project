package com.v1.authuser.dto;

/**
 * A generic response dto class.
 *
 * @param <T>   Data type.
 * @author Vansh Pratap Singh
 */
public class ExceptionResponseDto<T> {

    private Boolean success;

    private T details;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public T getDetails() {
        return details;
    }

    public void setDetails(T details) {
        this.details = details;
    }

}
