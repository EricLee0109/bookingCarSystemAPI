package com.bookingcar.service.mapper;

import com.bookingcar.domain.Brand;
import com.bookingcar.domain.CarModel;
import com.bookingcar.service.dto.BrandDTO;
import com.bookingcar.service.dto.CarModelDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link CarModel} and its DTO {@link CarModelDTO}.
 */
@Mapper(componentModel = "spring")
public interface CarModelMapper extends EntityMapper<CarModelDTO, CarModel> {
    @Mapping(target = "brand", source = "brand", qualifiedByName = "brandName")
    CarModelDTO toDto(CarModel s);

    @Named("brandName")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    BrandDTO toDtoBrandName(Brand brand);
}
