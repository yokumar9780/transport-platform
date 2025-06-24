package com.transport.platform.partnerservice.model;

import com.transport.platform.common.partnerservice.model.Address;
import com.transport.platform.common.partnerservice.model.Contact;
import com.transport.platform.common.partnerservice.model.Organization;
import com.transport.platform.common.partnerservice.model.PartnerType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "partners")
@Data
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public abstract class Partner implements Serializable {
    @Id
    private String id;
    private String name;
    private Contact contact;
    private Address address;
    private Organization organization;
    private PartnerType type;

    public Partner(PartnerType type) {
        this.type = type;
    }

}
