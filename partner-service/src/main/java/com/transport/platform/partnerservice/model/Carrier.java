package com.transport.platform.partnerservice.model;

import com.transport.platform.commonevents.partnerservice.model.PartnerType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.TypeAlias;

@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
@Data
@TypeAlias("CARRIER")
public class Carrier extends Partner {
    private int capacity;

    public Carrier() {
        super(PartnerType.CARRIER);
    }
}