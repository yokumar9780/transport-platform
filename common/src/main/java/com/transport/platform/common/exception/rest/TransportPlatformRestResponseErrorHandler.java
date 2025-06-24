package com.transport.platform.common.exception.rest;

import com.transport.platform.common.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.HttpServerErrorException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class TransportPlatformRestResponseErrorHandler extends DefaultResponseErrorHandler {

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        HttpStatus statusCode = HttpStatus.valueOf(response.getStatusCode().value());
        String errorMessage = getErrorMessage(response);

        switch (statusCode) {
            case NOT_FOUND -> throw new NotFoundException(errorMessage);
            case BAD_REQUEST -> throw new BadRequestException(errorMessage);
            case FORBIDDEN -> throw new PermissionDeniedException(errorMessage);
            case CONFLICT -> throw new ResourceExistsException(errorMessage);
            case UNAUTHORIZED -> throw new UnauthorizedException(errorMessage);
            case INTERNAL_SERVER_ERROR ->
                    throw new HttpServerErrorException(response.getStatusCode(), "Server error occurred: " + response.getStatusText());
            default -> super.handleError(response);
        }
    }

    private String getErrorMessage(ClientHttpResponse response) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(response.getBody()))) {
            return reader.lines().collect(Collectors.joining("\n"));
        }
    }


}

