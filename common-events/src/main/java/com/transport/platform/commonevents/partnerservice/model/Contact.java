package com.transport.platform.commonevents.partnerservice.model;

import java.io.Serializable;

public record Contact(String email, String phone) implements Serializable {
}