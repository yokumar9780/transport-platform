package com.transport.platform.commonevents.partnerservice.model;

import java.io.Serializable;


public record EventEnvelope<T>(
        T payload,
        String action,
        String type) implements Serializable {
}
