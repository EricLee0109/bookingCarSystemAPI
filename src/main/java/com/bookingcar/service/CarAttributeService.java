package com.bookingcar.service;

import com.bookingcar.domain.CarAttribute;
import com.bookingcar.repository.CarAttributeRepository;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link CarAttribute}.
 */
@Service
@Transactional
public class CarAttributeService {

    private final Logger log = LoggerFactory.getLogger(CarAttributeService.class);

    private final CarAttributeRepository carAttributeRepository;

    public CarAttributeService(CarAttributeRepository carAttributeRepository) {
        this.carAttributeRepository = carAttributeRepository;
    }

    /**
     * Save a carAttribute.
     *
     * @param carAttribute the entity to save.
     * @return the persisted entity.
     */
    public CarAttribute save(CarAttribute carAttribute) {
        log.debug("Request to save CarAttribute : {}", carAttribute);
        return carAttributeRepository.save(carAttribute);
    }

    /**
     * Update a carAttribute.
     *
     * @param carAttribute the entity to save.
     * @return the persisted entity.
     */
    public CarAttribute update(CarAttribute carAttribute) {
        log.debug("Request to update CarAttribute : {}", carAttribute);
        return carAttributeRepository.save(carAttribute);
    }

    /**
     * Partially update a carAttribute.
     *
     * @param carAttribute the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<CarAttribute> partialUpdate(CarAttribute carAttribute) {
        log.debug("Request to partially update CarAttribute : {}", carAttribute);

        return carAttributeRepository
            .findById(carAttribute.getId())
            .map(existingCarAttribute -> {
                if (carAttribute.getAttributeValue() != null) {
                    existingCarAttribute.setAttributeValue(carAttribute.getAttributeValue());
                }

                return existingCarAttribute;
            })
            .map(carAttributeRepository::save);
    }

    /**
     * Get all the carAttributes.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<CarAttribute> findAll() {
        log.debug("Request to get all CarAttributes");
        return carAttributeRepository.findAll();
    }

    /**
     * Get all the carAttributes with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<CarAttribute> findAllWithEagerRelationships(Pageable pageable) {
        return carAttributeRepository.findAllWithEagerRelationships(pageable);
    }

    /**
     * Get one carAttribute by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CarAttribute> findOne(Long id) {
        log.debug("Request to get CarAttribute : {}", id);
        return carAttributeRepository.findOneWithEagerRelationships(id);
    }

    /**
     * Delete the carAttribute by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CarAttribute : {}", id);
        carAttributeRepository.deleteById(id);
    }
}
