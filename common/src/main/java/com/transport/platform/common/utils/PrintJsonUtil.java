package com.transport.platform.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class PrintJsonUtil {
    private PrintJsonUtil() {
        throw new IllegalStateException("PrintJsonUtil class");
    }

    public static void log(Object source) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            log.debug(objectMapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(source));
        } catch (Exception ignore) {
            //ignore
            log.error("Failed to pretty print JSON. [{}]", ignore.getMessage());
        }
    }
}
