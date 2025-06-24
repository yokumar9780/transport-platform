package com.transport.platform.common.exception.rest;

import com.transport.platform.common.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException ex) {
        int value = HttpStatus.NOT_FOUND.value();
        ErrorResponse error = ErrorResponse.builder()
                .status(value)
                .timestamp(System.currentTimeMillis())
                .error(ex.getMessage())
                .build();
        return new ResponseEntity<>(error, HttpStatusCode.valueOf(value));
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException ex) {
        int value = HttpStatus.BAD_REQUEST.value();
        ErrorResponse error = ErrorResponse.builder()
                .status(value)
                .timestamp(System.currentTimeMillis())
                .error(ex.getMessage())
                .build();
        return new ResponseEntity<>(error, HttpStatusCode.valueOf(value));
    }


    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ErrorResponse> handleGlobalException(UnauthorizedException ex) {
        int value = HttpStatus.UNAUTHORIZED.value();
        ErrorResponse error = ErrorResponse.builder()
                .status(value)
                .timestamp(System.currentTimeMillis())
                .error(ex.getMessage())
                .build();
        return new ResponseEntity<>(error, HttpStatusCode.valueOf(value));
    }

    @ExceptionHandler(ResourceExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ErrorResponse> handleGlobalException(ResourceExistsException ex) {
        int value = HttpStatus.CONFLICT.value();
        ErrorResponse error = ErrorResponse.builder()
                .status(value)
                .timestamp(System.currentTimeMillis())
                .error(ex.getMessage())
                .build();
        return new ResponseEntity<>(error, HttpStatusCode.valueOf(value));
    }

    @ExceptionHandler(PermissionDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<ErrorResponse> handleGlobalException(PermissionDeniedException ex) {
        int value = HttpStatus.FORBIDDEN.value();
        ErrorResponse error = ErrorResponse.builder()
                .status(value)
                .timestamp(System.currentTimeMillis())
                .error(ex.getMessage())
                .build();
        return new ResponseEntity<>(error, HttpStatusCode.valueOf(value));
    }

    // Fallback for any unhandled exceptions
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex) {
        int value = HttpStatus.INTERNAL_SERVER_ERROR.value();
        ErrorResponse error = ErrorResponse.builder()
                .status(value)
                .timestamp(System.currentTimeMillis())
                .error(ex.getMessage())
                .build();
        return new ResponseEntity<>(error, HttpStatusCode.valueOf(value));
    }
}