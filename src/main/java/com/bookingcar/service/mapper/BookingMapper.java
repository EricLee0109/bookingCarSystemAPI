package com.bookingcar.service.mapper;

import com.bookingcar.domain.Booking;
import com.bookingcar.domain.Car;
import com.bookingcar.domain.Customer;
import com.bookingcar.domain.Employee;
import com.bookingcar.service.dto.BookingDTO;
import com.bookingcar.service.dto.CarDTO;
import com.bookingcar.service.dto.CustomerDTO;
import com.bookingcar.service.dto.EmployeeDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Booking} and its DTO {@link BookingDTO}.
 */
@Mapper(componentModel = "spring")
public interface BookingMapper extends EntityMapper<BookingDTO, Booking> {
    @Mapping(target = "employee", source = "employee", qualifiedByName = "employeeId")
    @Mapping(target = "car", source = "car", qualifiedByName = "carId")
    @Mapping(target = "customer", source = "customer", qualifiedByName = "customerId")
    BookingDTO toDto(Booking s);

    @Named("employeeId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    EmployeeDTO toDtoEmployeeId(Employee employee);

    @Named("carId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CarDTO toDtoCarId(Car car);

    @Named("customerId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CustomerDTO toDtoCustomerId(Customer customer);
}
