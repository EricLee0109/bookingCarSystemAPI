package com.bookingcar.service;

import com.bookingcar.domain.Booking;
import com.bookingcar.repository.BookingRepository;
import java.util.List;
import java.util.Optional;

import com.bookingcar.repository.CarRepository;
import com.bookingcar.repository.CustomerRepository;
import com.bookingcar.repository.EmployeeRepository;
import com.bookingcar.web.rest.vm.BookingRequestVM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Booking}.
 */
@Service
@Transactional
public class BookingService {

    private final Logger log = LoggerFactory.getLogger(BookingService.class);

    private final BookingRepository bookingRepository;

    private final CustomerService customerService;
    private final CarService carService;
    private final CustomerRepository customerRepository;

    private final EmployeeService employeeService;
    private  final EmployeeRepository employeeRepository;
    private final CarRepository carRepository;

    public BookingService(BookingRepository bookingRepository, CustomerService customerService, CarService carService, EmployeeService employeeService,
                          CustomerRepository customerRepository,EmployeeRepository employeeRepository,CarRepository carRepository) {
        this.bookingRepository = bookingRepository;
        this.customerService = customerService;
        this.carService = carService;
        this.employeeService = employeeService;
        this.customerRepository= customerRepository;
        this.employeeRepository =employeeRepository;
        this.carRepository= carRepository;
    }

    /**
     * Save a booking.
     *
     * @param booking the entity to save.
     * @return the persisted entity.
     */
    public Booking save(Booking booking) {
        log.debug("Request to save Booking : {}", booking);
        return bookingRepository.save(booking);
    }

    /**
     * Update a booking.
     *
     * @param booking the entity to save.
     * @return the persisted entity.
     */
    public Booking update(Booking booking) {
        log.debug("Request to update Booking : {}", booking);
        return bookingRepository.save(booking);
    }

    /**
     * Partially update a booking.
     *
     * @param booking the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<Booking> partialUpdate(Booking booking) {
        log.debug("Request to partially update Booking : {}", booking);

        return bookingRepository
            .findById(booking.getId())
            .map(existingBooking -> {
                if (booking.getTimeSlot() != null) {
                    existingBooking.setTimeSlot(booking.getTimeSlot());
                }
                if (booking.getDate() != null) {
                    existingBooking.setDate(booking.getDate());
                }

                return existingBooking;
            })
            .map(bookingRepository::save);
    }

    public Booking createBooking(BookingRequestVM bookingRequestVM, long customerId) {
        Booking booking = new Booking();
        booking.setCustomer(customerRepository.findByUser_Id(customerId));
        booking.setCar(carService.findOne(bookingRequestVM.getCarId()).get());
        booking.setDate(bookingRequestVM.getDate());
        booking.setTimeSlot(bookingRequestVM.getSlot());
        booking.setEmployee(employeeRepository.findByCars_Id(bookingRequestVM.getCarId()));
        log.debug("Request to save Booking : {}", booking);
        return bookingRepository.save(booking);
    }

    /**
     * Get all the bookings.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Booking> findAll() {
        log.debug("Request to get all Bookings");
        return bookingRepository.findAll();
    }

    /**
     * Get one booking by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Booking> findOne(Long id) {
        log.debug("Request to get Booking : {}", id);
        return bookingRepository.findById(id);
    }

    /**
     * Delete the booking by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Booking : {}", id);
        bookingRepository.deleteById(id);
    }
}
