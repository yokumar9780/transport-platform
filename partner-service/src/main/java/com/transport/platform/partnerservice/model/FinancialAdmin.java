package com.transport.platform.partnerservice.model;

import com.transport.platform.common.partnerservice.model.PartnerType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.TypeAlias;

@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
@Data
@TypeAlias("FIN_ADMIN")
public class FinancialAdmin extends Partner {

    public FinancialAdmin() {
        super(PartnerType.FINANCIAL_ADMIN);
    }
}
