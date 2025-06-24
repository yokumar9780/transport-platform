package com.transport.platform.common.partnerservice.command.user;

import com.transport.platform.common.partnerservice.model.PartnerType;

import java.io.Serializable;
import java.util.Set;


public record UpdatePartnerUserCommand(
        String username, String partnerId, PartnerType partnerType,
        Set<String> roles,
        boolean active, String organizationId) implements Serializable {
}
