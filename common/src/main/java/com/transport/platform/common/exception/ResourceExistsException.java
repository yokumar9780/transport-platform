package com.transport.platform.common.exception;


import com.transport.platform.common.utils.I18Constants;
import org.springframework.http.HttpStatus;


public class ResourceExistsException extends TransportPlatformException {

    // HTTP status code associated with a Bad Request
    private static final HttpStatus HTTP_STATUS = HttpStatus.CONFLICT;


    public ResourceExistsException(String message) {
        super(HTTP_STATUS, message);
    }


    public ResourceExistsException(I18Constants messageKey) {
        super(HTTP_STATUS, messageKey);
    }


    public ResourceExistsException(I18Constants messageKey, Object... messageParams) {
        super(HTTP_STATUS, messageKey, messageParams);
    }
}
