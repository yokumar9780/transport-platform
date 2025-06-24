package com.transport.platform.partnerservice.model;

import com.transport.platform.common.partnerservice.model.PartnerType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.TypeAlias;

@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
@Data
@TypeAlias("OP_ADMIN")
public class OperationalAdmin extends Partner {
    public OperationalAdmin() {
        super(PartnerType.OPERATIONAL_ADMIN);
    }
}