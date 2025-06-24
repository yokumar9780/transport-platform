package com.transport.platform.commonevents.partnerservice.command.partner;

import com.transport.platform.commonevents.partnerservice.model.Address;
import com.transport.platform.commonevents.partnerservice.model.Contact;
import com.transport.platform.commonevents.partnerservice.model.Organization;

import java.io.Serializable;

public record UpdateBuyerCommand(
        //PARTNER FIELDS
        String name, String email, Organization organization, Contact contact,
        Address address,
        //BUYER FIELDS
        String buyerSpecificField) implements Serializable {
}

