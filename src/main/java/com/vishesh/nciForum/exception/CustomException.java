package com.vishesh.nciForum.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Custom exception class
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class CustomException extends RuntimeException{
    private String errorCode;
    private int status;

    public CustomException(String message, String errorCode, int status){
        super(message);
        this.errorCode = errorCode;
        this.status = status;
    }
}
