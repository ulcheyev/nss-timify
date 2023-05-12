package com.kyki.notificationmicrosevice.exception;

public class UpdateCollision extends RuntimeException
{
    public UpdateCollision(String message) {
        super(message);
    }

    public UpdateCollision(String message, Throwable cause) {
        super(message, cause);
    }
}
