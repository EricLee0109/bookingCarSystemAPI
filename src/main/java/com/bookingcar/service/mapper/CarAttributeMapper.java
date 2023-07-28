package com.bookingcar.service.mapper;

import com.bookingcar.domain.Attribute;
import com.bookingcar.domain.Car;
import com.bookingcar.domain.CarAttribute;
import com.bookingcar.service.dto.AttributeDTO;
import com.bookingcar.service.dto.CarAttributeDTO;
import com.bookingcar.service.dto.CarDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link CarAttribute} and its DTO {@link CarAttributeDTO}.
 */
@Mapper(componentModel = "spring")
public interface CarAttributeMapper extends EntityMapper<CarAttributeDTO, CarAttribute> {
    @Mapping(target = "attribute", source = "attribute", qualifiedByName = "attributeAttributeName")
    @Mapping(target = "car", source = "car", qualifiedByName = "carName")
    CarAttributeDTO toDto(CarAttribute s);

    @Named("attributeAttributeName")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "attributeName", source = "attributeName")
    AttributeDTO toDtoAttributeAttributeName(Attribute attribute);

    @Named("carName")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    CarDTO toDtoCarName(Car car);
}
