package com.bookingcar.service.mapper;

import com.bookingcar.domain.Car;
import com.bookingcar.domain.CarModel;
import com.bookingcar.domain.Customer;
import com.bookingcar.domain.ShowRoom;
import com.bookingcar.service.dto.CarDTO;
import com.bookingcar.service.dto.CarModelDTO;
import com.bookingcar.service.dto.CustomerDTO;
import com.bookingcar.service.dto.ShowRoomDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Car} and its DTO {@link CarDTO}.
 */
@Mapper(componentModel = "spring")
public interface CarMapper extends EntityMapper<CarDTO, Car> {
    @Mapping(target = "showroom", source = "showroom", qualifiedByName = "showRoomId")
    @Mapping(target = "customer", source = "customer", qualifiedByName = "customerId")
    @Mapping(target = "carmodel", source = "carmodel", qualifiedByName = "carModelId")
    CarDTO toDto(Car s);

    @Named("showRoomId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ShowRoomDTO toDtoShowRoomId(ShowRoom showRoom);

    @Named("customerId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CustomerDTO toDtoCustomerId(Customer customer);

    @Named("carModelId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CarModelDTO toDtoCarModelId(CarModel carModel);
}
