package com.bookingcar.service.mapper;

import com.bookingcar.domain.Attribute;
import com.bookingcar.domain.GroupAttribute;
import com.bookingcar.service.dto.AttributeDTO;
import com.bookingcar.service.dto.GroupAttributeDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Attribute} and its DTO {@link AttributeDTO}.
 */
@Mapper(componentModel = "spring")
public interface AttributeMapper extends EntityMapper<AttributeDTO, Attribute> {
    @Mapping(target = "groupattribute", source = "groupattribute", qualifiedByName = "groupAttributeGroupAttributeName")
    AttributeDTO toDto(Attribute s);

    @Named("groupAttributeGroupAttributeName")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "groupAttributeName", source = "groupAttributeName")
    GroupAttributeDTO toDtoGroupAttributeGroupAttributeName(GroupAttribute groupAttribute);
}
