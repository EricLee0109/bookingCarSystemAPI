package com.bookingcar.service.mapper;

import com.bookingcar.domain.Employee;
import com.bookingcar.domain.ShowRoom;
import com.bookingcar.service.dto.EmployeeDTO;
import com.bookingcar.service.dto.ShowRoomDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Employee} and its DTO {@link EmployeeDTO}.
 */
@Mapper(componentModel = "spring")
public interface EmployeeMapper extends EntityMapper<EmployeeDTO, Employee> {
    @Mapping(target = "showroom", source = "showroom", qualifiedByName = "showRoomAddress")
    EmployeeDTO toDto(Employee s);

    @Named("showRoomAddress")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "address", source = "address")
    ShowRoomDTO toDtoShowRoomAddress(ShowRoom showRoom);
}
