package com.bookingcar.web.rest;

import com.bookingcar.domain.Booking;
import com.bookingcar.domain.Customer;
import com.bookingcar.domain.User;
import com.bookingcar.repository.BookingRepository;
import com.bookingcar.repository.CustomerRepository;
import com.bookingcar.repository.UserRepository;
import com.bookingcar.service.BookingService;
import com.bookingcar.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.bookingcar.web.rest.vm.BookingRequestVM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.bookingcar.domain.Booking}.
 */
@RestController
@RequestMapping("/api")
public class BookingResource {

    private final Logger log = LoggerFactory.getLogger(BookingResource.class);

    private static final String ENTITY_NAME = "booking";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BookingService bookingService;

    private final BookingRepository bookingRepository;

    private final CustomerRepository customerRepository;

    private final UserRepository userRepository;

    public BookingResource(BookingService bookingService, BookingRepository bookingRepository, CustomerRepository customerRepository, UserRepository userRepository) {
        this.bookingService = bookingService;
        this.bookingRepository = bookingRepository;
        this.customerRepository = customerRepository;
        this.userRepository = userRepository;
    }

    /**
     * {@code POST  /bookings} : Create a new booking.
     *
     * @param booking the booking to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new booking, or with status {@code 400 (Bad Request)} if the booking has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bookings")
    public ResponseEntity<Booking> createBooking(@Valid @RequestBody Booking booking) throws URISyntaxException {
        log.debug("REST request to save Booking : {}", booking);
        if (booking.getId() != null) {
            throw new BadRequestAlertException("A new booking cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Booking result = bookingService.save(booking);
        return ResponseEntity
            .created(new URI("/api/bookings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bookings/:id} : Updates an existing booking.
     *
     * @param id the id of the booking to save.
     * @param booking the booking to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated booking,
     * or with status {@code 400 (Bad Request)} if the booking is not valid,
     * or with status {@code 500 (Internal Server Error)} if the booking couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bookings/{id}")
    public ResponseEntity<Booking> updateBooking(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody Booking booking
    ) throws URISyntaxException {
        log.debug("REST request to update Booking : {}, {}", id, booking);
        if (booking.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, booking.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bookingRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Booking result = bookingService.update(booking);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, booking.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /bookings/:id} : Partial updates given fields of an existing booking, field will ignore if it is null
     *
     * @param id the id of the booking to save.
     * @param booking the booking to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated booking,
     * or with status {@code 400 (Bad Request)} if the booking is not valid,
     * or with status {@code 404 (Not Found)} if the booking is not found,
     * or with status {@code 500 (Internal Server Error)} if the booking couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/bookings/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Booking> partialUpdateBooking(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Booking booking
    ) throws URISyntaxException {
        log.debug("REST request to partial update Booking partially : {}, {}", id, booking);
        if (booking.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, booking.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bookingRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Booking> result = bookingService.partialUpdate(booking);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, booking.getId().toString())
        );
    }

    /**
     * {@code GET  /bookings} : get all the bookings.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bookings in body.
     */
    @GetMapping("/bookings")
    public List<Booking> getAllBookings() {
        log.debug("REST request to get all Bookings");
        return bookingService.findAll();
    }

    @GetMapping("/bookings/user")
    public List<Booking> getAllBookings(Principal principal) {
        log.debug("REST request to get all Bookings");
        Optional<User> user = userRepository.findOneByLogin(principal.getName());
        List<Booking> bookings = bookingRepository.findByCustomer_UserId(user.get().getId());
        return bookings;
    }

    /**
     * {@code GET  /bookings/:id} : get the "id" booking.
     *
     * @param id the id of the booking to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the booking, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bookings/{id}")
    public ResponseEntity<Booking> getBooking(@PathVariable Long id) {
        log.debug("REST request to get Booking : {}", id);
        Optional<Booking> booking = bookingService.findOne(id);
        return ResponseUtil.wrapOrNotFound(booking);
    }

    /**
     * {@code DELETE  /bookings/:id} : delete the "id" booking.
     *
     * @param id the id of the booking to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bookings/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        log.debug("REST request to delete Booking : {}", id);
        bookingService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    @PostMapping("/bookings/test")
    public ResponseEntity<Booking> createBookingTest(@Valid @RequestBody BookingRequestVM bookingRequest, Principal principal) throws URISyntaxException {
        log.debug("REST request to save Booking : {}", bookingRequest);
        log.debug("test booking: {}, {}", bookingRequest, principal);

        if (bookingRequest.getCarId() == null || bookingRequest.getDate() == null || bookingRequest.getSlot() == 0) {
            throw new BadRequestAlertException("Request payload invalid", ENTITY_NAME, "idexists");
        }

        Booking checkBooking = bookingRepository.findByCar_IdAndTimeSlotAndDate(bookingRequest.getCarId(), bookingRequest.getSlot(), bookingRequest.getDate());
        if (checkBooking != null)
            throw new BadRequestAlertException("Car has been booked", ENTITY_NAME, "idexists");

        Optional<User> user = userRepository.findOneByLogin(principal.getName());
        log.debug("test booking: {}", user.get().getId());
        Booking booked = bookingService.createBooking(bookingRequest, user.get().getId());

        return ResponseEntity
            .created(new URI("/api/bookings/" + booked.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, booked.getId().toString()))
            .body(booked);
    }
}
