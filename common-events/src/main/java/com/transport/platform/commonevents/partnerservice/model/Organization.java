package com.transport.platform.commonevents.partnerservice.model;


import java.io.Serializable;

public record Organization(String orgId, String name, String type) implements Serializable {
}