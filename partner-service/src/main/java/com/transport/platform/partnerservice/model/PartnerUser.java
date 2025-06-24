package com.transport.platform.partnerservice.model;

import com.transport.platform.commonevents.partnerservice.model.PartnerType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document(collection = "users")
@Data
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class PartnerUser {
    @Id
    private String id;

    private String username;  // email or login
    private String partnerId;     // reference to Buyer/Carrier/Admin
    private PartnerType partnerType; // BUYER, CARRIER, etc.

    private Set<String> roles;  // e.g., ["OPS_ADMIN", "FIN_ADMIN"]
    private boolean active;
    private String organizationId;
}