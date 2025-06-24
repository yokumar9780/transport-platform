package com.transport.platform.commonevents.partnerservice.model;


import java.io.Serializable;

public record Address(String city, String country, String postalCode, String street) implements Serializable {
}