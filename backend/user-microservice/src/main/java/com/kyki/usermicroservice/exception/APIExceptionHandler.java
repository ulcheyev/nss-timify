package com.kyki.usermicroservice.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
@Slf4j
public class APIExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {NotFoundException.class, ValidationException.class, AccessDeniedException.class})
    protected ResponseEntity<Object> handleConflict(Exception ex, WebRequest req) {
        log.error(ex.getMessage() + " " + req);
        StandardExceptionPayload load = StandardExceptionPayload
                .builder()
                .message(ex.getMessage())
                .httpStatus(HttpStatus.CONFLICT)
                .zonedDateTime(ZonedDateTime.now())
                .build();
        return new ResponseEntity<>(load, load.getHttpStatus());
    }




}
