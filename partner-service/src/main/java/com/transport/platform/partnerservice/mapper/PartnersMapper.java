package com.transport.platform.partnerservice.mapper;


import com.transport.platform.commonevents.partnerservice.command.partner.*;
import com.transport.platform.partnerservice.model.Buyer;
import com.transport.platform.partnerservice.model.Carrier;
import com.transport.platform.partnerservice.model.FinancialAdmin;
import com.transport.platform.partnerservice.model.OperationalAdmin;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {com.transport.platform.partnerservice.mapper.BaseMapper.class})
public interface PartnersMapper {
    @Mapping(target = "type", constant = "BUYER")
    @Mapping(target = "id", ignore = true)
    Buyer map(CreateBuyerCommand source);

    @Mapping(target = "type", constant = "BUYER")
    @Mapping(target = "id", ignore = true)
    void map(@MappingTarget Buyer target, UpdateBuyerCommand source);

    @Mapping(target = "type", constant = "CARRIER")
    @Mapping(target = "id", ignore = true)
    Carrier map(CreateCarrierCommand command);

    @Mapping(target = "type", constant = "CARRIER")
    @Mapping(target = "id", ignore = true)
    void map(@MappingTarget Carrier target, UpdateCarrierCommand source);

    @Mapping(target = "type", constant = "OPERATIONAL_ADMIN")
    @Mapping(target = "id", ignore = true)
    OperationalAdmin map(CreateOperationalAdminCommand command);

    @Mapping(target = "type", constant = "FINANCIAL_ADMIN")
    @Mapping(target = "id", ignore = true)
    FinancialAdmin map(CreateFinancialAdminCommand command);
}
