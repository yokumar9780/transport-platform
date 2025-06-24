package com.transport.platform.partnerservice.model;


import com.transport.platform.commonevents.partnerservice.model.PartnerType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.TypeAlias;

@TypeAlias("BUYER")
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
@Data
public class Buyer extends Partner {
    private String buyerSpecificField;

    public Buyer() {
        super(PartnerType.BUYER);
    }
}
