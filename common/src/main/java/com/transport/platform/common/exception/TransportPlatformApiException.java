package com.transport.platform.common.exception;


import org.springframework.http.HttpStatus;

public class TransportPlatformApiException extends TransportPlatformException {

    public TransportPlatformApiException(String message, HttpStatus httpStatus) {
        super(httpStatus, message);
    }

}
