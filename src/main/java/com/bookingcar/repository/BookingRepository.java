package com.bookingcar.repository;

import com.bookingcar.domain.Booking;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Spring Data JPA repository for the Booking entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByCustomer_UserId(long userId);
    List<Booking> findByCustomer_Id(long customerId);
    Booking findByCar_IdAndTimeSlotAndDate(long carId, int timeslot, LocalDate date);
}
