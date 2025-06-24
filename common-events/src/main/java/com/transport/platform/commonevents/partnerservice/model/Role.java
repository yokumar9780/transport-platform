package com.transport.platform.commonevents.partnerservice.model;


import java.io.Serializable;

public record Role(
        String name // OPS_ADMIN, FIN_ADMIN, etc.
) implements Serializable {
}