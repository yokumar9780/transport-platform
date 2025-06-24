package com.transport.platform.partnerservice.mapper;


import com.transport.platform.commonevents.partnerservice.command.user.CreatePartnerUserCommand;
import com.transport.platform.commonevents.partnerservice.command.user.UpdatePartnerUserCommand;
import com.transport.platform.partnerservice.model.PartnerUser;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {BaseMapper.class})
public interface PartnerUsersMapper {
    @Mapping(target = "id", ignore = true)
    PartnerUser map(CreatePartnerUserCommand source);

    @Mapping(target = "id", ignore = true)
    PartnerUser map(@MappingTarget PartnerUser target, UpdatePartnerUserCommand source);
}
