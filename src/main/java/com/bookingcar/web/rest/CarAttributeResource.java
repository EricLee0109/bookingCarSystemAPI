package com.bookingcar.web.rest;

import com.bookingcar.domain.CarAttribute;
import com.bookingcar.repository.CarAttributeRepository;
import com.bookingcar.service.CarAttributeService;
import com.bookingcar.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.bookingcar.domain.CarAttribute}.
 */
@RestController
@RequestMapping("/api")
public class CarAttributeResource {

    private final Logger log = LoggerFactory.getLogger(CarAttributeResource.class);

    private static final String ENTITY_NAME = "carAttribute";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CarAttributeService carAttributeService;

    private final CarAttributeRepository carAttributeRepository;

    public CarAttributeResource(CarAttributeService carAttributeService, CarAttributeRepository carAttributeRepository) {
        this.carAttributeService = carAttributeService;
        this.carAttributeRepository = carAttributeRepository;
    }

    /**
     * {@code POST  /car-attributes} : Create a new carAttribute.
     *
     * @param carAttribute the carAttribute to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new carAttribute, or with status {@code 400 (Bad Request)} if the carAttribute has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/car-attributes")
    public ResponseEntity<CarAttribute> createCarAttribute(@Valid @RequestBody CarAttribute carAttribute) throws URISyntaxException {
        log.debug("REST request to save CarAttribute : {}", carAttribute);
        if (carAttribute.getId() != null) {
            throw new BadRequestAlertException("A new carAttribute cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CarAttribute result = carAttributeService.save(carAttribute);
        return ResponseEntity
            .created(new URI("/api/car-attributes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /car-attributes/:id} : Updates an existing carAttribute.
     *
     * @param id the id of the carAttribute to save.
     * @param carAttribute the carAttribute to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated carAttribute,
     * or with status {@code 400 (Bad Request)} if the carAttribute is not valid,
     * or with status {@code 500 (Internal Server Error)} if the carAttribute couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/car-attributes/{id}")
    public ResponseEntity<CarAttribute> updateCarAttribute(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody CarAttribute carAttribute
    ) throws URISyntaxException {
        log.debug("REST request to update CarAttribute : {}, {}", id, carAttribute);
        if (carAttribute.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, carAttribute.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!carAttributeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        CarAttribute result = carAttributeService.update(carAttribute);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, carAttribute.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /car-attributes/:id} : Partial updates given fields of an existing carAttribute, field will ignore if it is null
     *
     * @param id the id of the carAttribute to save.
     * @param carAttribute the carAttribute to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated carAttribute,
     * or with status {@code 400 (Bad Request)} if the carAttribute is not valid,
     * or with status {@code 404 (Not Found)} if the carAttribute is not found,
     * or with status {@code 500 (Internal Server Error)} if the carAttribute couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/car-attributes/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<CarAttribute> partialUpdateCarAttribute(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody CarAttribute carAttribute
    ) throws URISyntaxException {
        log.debug("REST request to partial update CarAttribute partially : {}, {}", id, carAttribute);
        if (carAttribute.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, carAttribute.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!carAttributeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CarAttribute> result = carAttributeService.partialUpdate(carAttribute);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, carAttribute.getId().toString())
        );
    }

    /**
     * {@code GET  /car-attributes} : get all the carAttributes.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of carAttributes in body.
     */
    @GetMapping("/car-attributes")
    public List<CarAttribute> getAllCarAttributes(@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get all CarAttributes");
        return carAttributeService.findAll();
    }

    /**
     * {@code GET  /car-attributes/:id} : get the "id" carAttribute.
     *
     * @param id the id of the carAttribute to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the carAttribute, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/car-attributes/{id}")
    public ResponseEntity<CarAttribute> getCarAttribute(@PathVariable Long id) {
        log.debug("REST request to get CarAttribute : {}", id);
        Optional<CarAttribute> carAttribute = carAttributeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(carAttribute);
    }

    /**
     * {@code DELETE  /car-attributes/:id} : delete the "id" carAttribute.
     *
     * @param id the id of the carAttribute to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/car-attributes/{id}")
    public ResponseEntity<Void> deleteCarAttribute(@PathVariable Long id) {
        log.debug("REST request to delete CarAttribute : {}", id);
        carAttributeService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
