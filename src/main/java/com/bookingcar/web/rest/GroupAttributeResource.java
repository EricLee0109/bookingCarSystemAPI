package com.bookingcar.web.rest;

import com.bookingcar.domain.GroupAttribute;
import com.bookingcar.repository.GroupAttributeRepository;
import com.bookingcar.service.GroupAttributeService;
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
 * REST controller for managing {@link com.bookingcar.domain.GroupAttribute}.
 */
@RestController
@RequestMapping("/api")
public class GroupAttributeResource {

    private final Logger log = LoggerFactory.getLogger(GroupAttributeResource.class);

    private static final String ENTITY_NAME = "groupAttribute";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GroupAttributeService groupAttributeService;

    private final GroupAttributeRepository groupAttributeRepository;

    public GroupAttributeResource(GroupAttributeService groupAttributeService, GroupAttributeRepository groupAttributeRepository) {
        this.groupAttributeService = groupAttributeService;
        this.groupAttributeRepository = groupAttributeRepository;
    }

    /**
     * {@code POST  /group-attributes} : Create a new groupAttribute.
     *
     * @param groupAttribute the groupAttribute to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new groupAttribute, or with status {@code 400 (Bad Request)} if the groupAttribute has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/group-attributes")
    public ResponseEntity<GroupAttribute> createGroupAttribute(@Valid @RequestBody GroupAttribute groupAttribute)
        throws URISyntaxException {
        log.debug("REST request to save GroupAttribute : {}", groupAttribute);
        if (groupAttribute.getId() != null) {
            throw new BadRequestAlertException("A new groupAttribute cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GroupAttribute result = groupAttributeService.save(groupAttribute);
        return ResponseEntity
            .created(new URI("/api/group-attributes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /group-attributes/:id} : Updates an existing groupAttribute.
     *
     * @param id the id of the groupAttribute to save.
     * @param groupAttribute the groupAttribute to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated groupAttribute,
     * or with status {@code 400 (Bad Request)} if the groupAttribute is not valid,
     * or with status {@code 500 (Internal Server Error)} if the groupAttribute couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/group-attributes/{id}")
    public ResponseEntity<GroupAttribute> updateGroupAttribute(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody GroupAttribute groupAttribute
    ) throws URISyntaxException {
        log.debug("REST request to update GroupAttribute : {}, {}", id, groupAttribute);
        if (groupAttribute.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, groupAttribute.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!groupAttributeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        GroupAttribute result = groupAttributeService.update(groupAttribute);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, groupAttribute.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /group-attributes/:id} : Partial updates given fields of an existing groupAttribute, field will ignore if it is null
     *
     * @param id the id of the groupAttribute to save.
     * @param groupAttribute the groupAttribute to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated groupAttribute,
     * or with status {@code 400 (Bad Request)} if the groupAttribute is not valid,
     * or with status {@code 404 (Not Found)} if the groupAttribute is not found,
     * or with status {@code 500 (Internal Server Error)} if the groupAttribute couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/group-attributes/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<GroupAttribute> partialUpdateGroupAttribute(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody GroupAttribute groupAttribute
    ) throws URISyntaxException {
        log.debug("REST request to partial update GroupAttribute partially : {}, {}", id, groupAttribute);
        if (groupAttribute.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, groupAttribute.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!groupAttributeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<GroupAttribute> result = groupAttributeService.partialUpdate(groupAttribute);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, groupAttribute.getId().toString())
        );
    }

    /**
     * {@code GET  /group-attributes} : get all the groupAttributes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of groupAttributes in body.
     */
    @GetMapping("/group-attributes")
    public List<GroupAttribute> getAllGroupAttributes() {
        log.debug("REST request to get all GroupAttributes");
        return groupAttributeService.findAll();
    }

    /**
     * {@code GET  /group-attributes/:id} : get the "id" groupAttribute.
     *
     * @param id the id of the groupAttribute to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the groupAttribute, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/group-attributes/{id}")
    public ResponseEntity<GroupAttribute> getGroupAttribute(@PathVariable Long id) {
        log.debug("REST request to get GroupAttribute : {}", id);
        Optional<GroupAttribute> groupAttribute = groupAttributeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(groupAttribute);
    }

    /**
     * {@code DELETE  /group-attributes/:id} : delete the "id" groupAttribute.
     *
     * @param id the id of the groupAttribute to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/group-attributes/{id}")
    public ResponseEntity<Void> deleteGroupAttribute(@PathVariable Long id) {
        log.debug("REST request to delete GroupAttribute : {}", id);
        groupAttributeService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
