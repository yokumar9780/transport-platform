package com.transport.platform.common.exception.rest;

import lombok.Builder;

@Builder
public record ErrorResponse(
        int status,
        String error,
        long timestamp) {

}
