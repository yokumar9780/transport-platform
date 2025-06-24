package com.transport.platform.common.utils;

import lombok.Getter;

@Getter
public enum I18Constants {
    TRANSPORT_ORDER_NOT_FOUND("transport.order.not.found");

    private final String key;

    I18Constants(final String key) {
        this.key = key;
    }

}
