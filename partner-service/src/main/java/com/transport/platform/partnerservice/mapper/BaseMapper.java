package com.transport.platform.partnerservice.mapper;

import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Condition;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Mapper(componentModel = "spring", collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public class BaseMapper {

    @Condition
    boolean isNotEmpty(String value) {
        return StringUtils.hasText(value);
    }

    @Condition
    boolean isNotEmpty(Objects value) {
        return !ObjectUtils.isEmpty(value);
    }


}
