package com.bookingcar.service.mapper;

import com.bookingcar.domain.Car;
import com.bookingcar.domain.CarImage;
import com.bookingcar.service.dto.CarDTO;
import com.bookingcar.service.dto.CarImageDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link CarImage} and its DTO {@link CarImageDTO}.
 */
@Mapper(componentModel = "spring")
public interface CarImageMapper extends EntityMapper<CarImageDTO, CarImage> {
    @Mapping(target = "car", source = "car", qualifiedByName = "carName")
    CarImageDTO toDto(CarImage s);

    @Named("carName")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    CarDTO toDtoCarName(Car car);
}
