package com.transport.platform.common.exception;


import com.transport.platform.common.utils.I18Constants;
import org.springframework.http.HttpStatus;


public class UnauthorizedException extends TransportPlatformException {

    private static final HttpStatus HTTP_STATUS = HttpStatus.UNAUTHORIZED;


    public UnauthorizedException(String message) {
        super(HTTP_STATUS, message);
    }


    public UnauthorizedException(I18Constants messageKey) {
        super(HTTP_STATUS, messageKey);
    }

    public UnauthorizedException(I18Constants messageKey, Object... messageParams) {
        super(HTTP_STATUS, messageKey, messageParams);
    }
}
