package com.kyki.usermicroservice.exception;

public class NotFoundException extends RuntimeException{

    public NotFoundException(String str) {
        super(str);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
