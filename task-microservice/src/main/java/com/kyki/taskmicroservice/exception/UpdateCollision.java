package com.kyki.taskmicroservice.exception;

public class UpdateCollision extends RuntimeException
{
    public UpdateCollision(String message) {
        super(message);
    }

    public UpdateCollision(String message, Throwable cause) {
        super(message, cause);
    }
}
