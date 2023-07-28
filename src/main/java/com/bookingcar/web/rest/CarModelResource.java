package com.bookingcar.web.rest;

import com.bookingcar.domain.CarModel;
import com.bookingcar.repository.CarModelRepository;
import com.bookingcar.service.CarModelService;
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
 * REST controller for managing {@link com.bookingcar.domain.CarModel}.
 */
@RestController
@RequestMapping("/api")
public class CarModelResource {

    private final Logger log = LoggerFactory.getLogger(CarModelResource.class);

    private static final String ENTITY_NAME = "carModel";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CarModelService carModelService;

    private final CarModelRepository carModelRepository;

    public CarModelResource(CarModelService carModelService, CarModelRepository carModelRepository) {
        this.carModelService = carModelService;
        this.carModelRepository = carModelRepository;
    }

    /**
     * {@code POST  /car-models} : Create a new carModel.
     *
     * @param carModel the carModel to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new carModel, or with status {@code 400 (Bad Request)} if the carModel has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/car-models")
    public ResponseEntity<CarModel> createCarModel(@Valid @RequestBody CarModel carModel) throws URISyntaxException {
        log.debug("REST request to save CarModel : {}", carModel);
        if (carModel.getId() != null) {
            throw new BadRequestAlertException("A new carModel cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CarModel result = carModelService.save(carModel);
        return ResponseEntity
            .created(new URI("/api/car-models/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /car-models/:id} : Updates an existing carModel.
     *
     * @param id the id of the carModel to save.
     * @param carModel the carModel to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated carModel,
     * or with status {@code 400 (Bad Request)} if the carModel is not valid,
     * or with status {@code 500 (Internal Server Error)} if the carModel couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/car-models/{id}")
    public ResponseEntity<CarModel> updateCarModel(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody CarModel carModel
    ) throws URISyntaxException {
        log.debug("REST request to update CarModel : {}, {}", id, carModel);
        if (carModel.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, carModel.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!carModelRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        CarModel result = carModelService.update(carModel);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, carModel.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /car-models/:id} : Partial updates given fields of an existing carModel, field will ignore if it is null
     *
     * @param id the id of the carModel to save.
     * @param carModel the carModel to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated carModel,
     * or with status {@code 400 (Bad Request)} if the carModel is not valid,
     * or with status {@code 404 (Not Found)} if the carModel is not found,
     * or with status {@code 500 (Internal Server Error)} if the carModel couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/car-models/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<CarModel> partialUpdateCarModel(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody CarModel carModel
    ) throws URISyntaxException {
        log.debug("REST request to partial update CarModel partially : {}, {}", id, carModel);
        if (carModel.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, carModel.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!carModelRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CarModel> result = carModelService.partialUpdate(carModel);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, carModel.getId().toString())
        );
    }

    /**
     * {@code GET  /car-models} : get all the carModels.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of carModels in body.
     */
    @GetMapping("/car-models")
    public List<CarModel> getAllCarModels(@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get all CarModels");
        return carModelService.findAll();
    }

    /**
     * {@code GET  /car-models/:id} : get the "id" carModel.
     *
     * @param id the id of the carModel to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the carModel, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/car-models/{id}")
    public ResponseEntity<CarModel> getCarModel(@PathVariable Long id) {
        log.debug("REST request to get CarModel : {}", id);
        Optional<CarModel> carModel = carModelService.findOne(id);
        return ResponseUtil.wrapOrNotFound(carModel);
    }

    /**
     * {@code DELETE  /car-models/:id} : delete the "id" carModel.
     *
     * @param id the id of the carModel to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/car-models/{id}")
    public ResponseEntity<Void> deleteCarModel(@PathVariable Long id) {
        log.debug("REST request to delete CarModel : {}", id);
        carModelService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
