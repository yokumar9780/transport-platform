package com.transport.platform.common.partnerservice.command.partner;

import com.transport.platform.common.partnerservice.model.Address;
import com.transport.platform.common.partnerservice.model.Contact;
import com.transport.platform.common.partnerservice.model.Organization;

import java.io.Serializable;


public record CreateOperationalAdminCommand(
        //PARTNER FIELDS
        String name, String email, Organization organization, Contact contact,
        Address address
        //OPERATIONAL ADMIN FIELDS
) implements Serializable {
}