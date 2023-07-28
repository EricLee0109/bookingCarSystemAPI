package com.bookingcar.service;

import com.bookingcar.domain.Attribute;
import com.bookingcar.repository.AttributeRepository;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Attribute}.
 */
@Service
@Transactional
public class AttributeService {

    private final Logger log = LoggerFactory.getLogger(AttributeService.class);

    private final AttributeRepository attributeRepository;

    public AttributeService(AttributeRepository attributeRepository) {
        this.attributeRepository = attributeRepository;
    }

    /**
     * Save a attribute.
     *
     * @param attribute the entity to save.
     * @return the persisted entity.
     */
    public Attribute save(Attribute attribute) {
        log.debug("Request to save Attribute : {}", attribute);
        return attributeRepository.save(attribute);
    }

    /**
     * Update a attribute.
     *
     * @param attribute the entity to save.
     * @return the persisted entity.
     */
    public Attribute update(Attribute attribute) {
        log.debug("Request to update Attribute : {}", attribute);
        return attributeRepository.save(attribute);
    }

    /**
     * Partially update a attribute.
     *
     * @param attribute the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<Attribute> partialUpdate(Attribute attribute) {
        log.debug("Request to partially update Attribute : {}", attribute);

        return attributeRepository
            .findById(attribute.getId())
            .map(existingAttribute -> {
                if (attribute.getAttributeName() != null) {
                    existingAttribute.setAttributeName(attribute.getAttributeName());
                }
                if (attribute.getDisplayIndex() != null) {
                    existingAttribute.setDisplayIndex(attribute.getDisplayIndex());
                }

                return existingAttribute;
            })
            .map(attributeRepository::save);
    }

    /**
     * Get all the attributes.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Attribute> findAll() {
        log.debug("Request to get all Attributes");
        return attributeRepository.findAll();
    }

    /**
     * Get all the attributes with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<Attribute> findAllWithEagerRelationships(Pageable pageable) {
        return attributeRepository.findAllWithEagerRelationships(pageable);
    }

    /**
     * Get one attribute by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Attribute> findOne(Long id) {
        log.debug("Request to get Attribute : {}", id);
        return attributeRepository.findOneWithEagerRelationships(id);
    }

    /**
     * Delete the attribute by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Attribute : {}", id);
        attributeRepository.deleteById(id);
    }
}
