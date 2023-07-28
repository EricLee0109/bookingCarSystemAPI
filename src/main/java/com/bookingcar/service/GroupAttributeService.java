package com.bookingcar.service;

import com.bookingcar.domain.GroupAttribute;
import com.bookingcar.repository.GroupAttributeRepository;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link GroupAttribute}.
 */
@Service
@Transactional
public class GroupAttributeService {

    private final Logger log = LoggerFactory.getLogger(GroupAttributeService.class);

    private final GroupAttributeRepository groupAttributeRepository;

    public GroupAttributeService(GroupAttributeRepository groupAttributeRepository) {
        this.groupAttributeRepository = groupAttributeRepository;
    }

    /**
     * Save a groupAttribute.
     *
     * @param groupAttribute the entity to save.
     * @return the persisted entity.
     */
    public GroupAttribute save(GroupAttribute groupAttribute) {
        log.debug("Request to save GroupAttribute : {}", groupAttribute);
        return groupAttributeRepository.save(groupAttribute);
    }

    /**
     * Update a groupAttribute.
     *
     * @param groupAttribute the entity to save.
     * @return the persisted entity.
     */
    public GroupAttribute update(GroupAttribute groupAttribute) {
        log.debug("Request to update GroupAttribute : {}", groupAttribute);
        return groupAttributeRepository.save(groupAttribute);
    }

    /**
     * Partially update a groupAttribute.
     *
     * @param groupAttribute the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<GroupAttribute> partialUpdate(GroupAttribute groupAttribute) {
        log.debug("Request to partially update GroupAttribute : {}", groupAttribute);

        return groupAttributeRepository
            .findById(groupAttribute.getId())
            .map(existingGroupAttribute -> {
                if (groupAttribute.getGroupAttributeName() != null) {
                    existingGroupAttribute.setGroupAttributeName(groupAttribute.getGroupAttributeName());
                }
                if (groupAttribute.getDisplayIndex() != null) {
                    existingGroupAttribute.setDisplayIndex(groupAttribute.getDisplayIndex());
                }

                return existingGroupAttribute;
            })
            .map(groupAttributeRepository::save);
    }

    /**
     * Get all the groupAttributes.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<GroupAttribute> findAll() {
        log.debug("Request to get all GroupAttributes");
        return groupAttributeRepository.findAll();
    }

    /**
     * Get one groupAttribute by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<GroupAttribute> findOne(Long id) {
        log.debug("Request to get GroupAttribute : {}", id);
        return groupAttributeRepository.findById(id);
    }

    /**
     * Delete the groupAttribute by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete GroupAttribute : {}", id);
        groupAttributeRepository.deleteById(id);
    }
}
