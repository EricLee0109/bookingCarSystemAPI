package com.bookingcar.web.rest;

import com.bookingcar.domain.ShowRoom;
import com.bookingcar.repository.ShowRoomRepository;
import com.bookingcar.service.ShowRoomService;
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
 * REST controller for managing {@link com.bookingcar.domain.ShowRoom}.
 */
@RestController
@RequestMapping("/api")
public class ShowRoomResource {

    private final Logger log = LoggerFactory.getLogger(ShowRoomResource.class);

    private static final String ENTITY_NAME = "showRoom";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ShowRoomService showRoomService;

    private final ShowRoomRepository showRoomRepository;

    public ShowRoomResource(ShowRoomService showRoomService, ShowRoomRepository showRoomRepository) {
        this.showRoomService = showRoomService;
        this.showRoomRepository = showRoomRepository;
    }

    /**
     * {@code POST  /show-rooms} : Create a new showRoom.
     *
     * @param showRoom the showRoom to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new showRoom, or with status {@code 400 (Bad Request)} if the showRoom has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/show-rooms")
    public ResponseEntity<ShowRoom> createShowRoom(@Valid @RequestBody ShowRoom showRoom) throws URISyntaxException {
        log.debug("REST request to save ShowRoom : {}", showRoom);
        if (showRoom.getId() != null) {
            throw new BadRequestAlertException("A new showRoom cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ShowRoom result = showRoomService.save(showRoom);
        return ResponseEntity
            .created(new URI("/api/show-rooms/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /show-rooms/:id} : Updates an existing showRoom.
     *
     * @param id the id of the showRoom to save.
     * @param showRoom the showRoom to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated showRoom,
     * or with status {@code 400 (Bad Request)} if the showRoom is not valid,
     * or with status {@code 500 (Internal Server Error)} if the showRoom couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/show-rooms/{id}")
    public ResponseEntity<ShowRoom> updateShowRoom(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody ShowRoom showRoom
    ) throws URISyntaxException {
        log.debug("REST request to update ShowRoom : {}, {}", id, showRoom);
        if (showRoom.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, showRoom.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!showRoomRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ShowRoom result = showRoomService.update(showRoom);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, showRoom.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /show-rooms/:id} : Partial updates given fields of an existing showRoom, field will ignore if it is null
     *
     * @param id the id of the showRoom to save.
     * @param showRoom the showRoom to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated showRoom,
     * or with status {@code 400 (Bad Request)} if the showRoom is not valid,
     * or with status {@code 404 (Not Found)} if the showRoom is not found,
     * or with status {@code 500 (Internal Server Error)} if the showRoom couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/show-rooms/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ShowRoom> partialUpdateShowRoom(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody ShowRoom showRoom
    ) throws URISyntaxException {
        log.debug("REST request to partial update ShowRoom partially : {}, {}", id, showRoom);
        if (showRoom.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, showRoom.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!showRoomRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ShowRoom> result = showRoomService.partialUpdate(showRoom);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, showRoom.getId().toString())
        );
    }

    /**
     * {@code GET  /show-rooms} : get all the showRooms.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of showRooms in body.
     */
    @GetMapping("/show-rooms")
    public List<ShowRoom> getAllShowRooms() {
        log.debug("REST request to get all ShowRooms");
        return showRoomService.findAll();
    }

    /**
     * {@code GET  /show-rooms/:id} : get the "id" showRoom.
     *
     * @param id the id of the showRoom to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the showRoom, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/show-rooms/{id}")
    public ResponseEntity<ShowRoom> getShowRoom(@PathVariable Long id) {
        log.debug("REST request to get ShowRoom : {}", id);
        Optional<ShowRoom> showRoom = showRoomService.findOne(id);
        return ResponseUtil.wrapOrNotFound(showRoom);
    }

    /**
     * {@code DELETE  /show-rooms/:id} : delete the "id" showRoom.
     *
     * @param id the id of the showRoom to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/show-rooms/{id}")
    public ResponseEntity<Void> deleteShowRoom(@PathVariable Long id) {
        log.debug("REST request to delete ShowRoom : {}", id);
        showRoomService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
