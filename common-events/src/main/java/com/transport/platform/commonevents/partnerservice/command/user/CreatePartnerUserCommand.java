package com.transport.platform.commonevents.partnerservice.command.user;

import com.transport.platform.commonevents.partnerservice.model.PartnerType;

import java.io.Serializable;
import java.util.Set;


public record CreatePartnerUserCommand(
        String username, String partnerId, PartnerType partnerType,
        Set<String> roles,
        boolean active, String organizationId) implements Serializable {
}
