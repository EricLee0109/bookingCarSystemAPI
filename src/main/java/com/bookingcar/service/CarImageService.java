package com.bookingcar.service;

import com.bookingcar.domain.CarImage;
import com.bookingcar.repository.CarImageRepository;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link CarImage}.
 */
@Service
@Transactional
public class CarImageService {

    private final Logger log = LoggerFactory.getLogger(CarImageService.class);

    private final CarImageRepository carImageRepository;

    public CarImageService(CarImageRepository carImageRepository) {
        this.carImageRepository = carImageRepository;
    }

    /**
     * Save a carImage.
     *
     * @param carImage the entity to save.
     * @return the persisted entity.
     */
    public CarImage save(CarImage carImage) {
        log.debug("Request to save CarImage : {}", carImage);
        return carImageRepository.save(carImage);
    }

    /**
     * Update a carImage.
     *
     * @param carImage the entity to save.
     * @return the persisted entity.
     */
    public CarImage update(CarImage carImage) {
        log.debug("Request to update CarImage : {}", carImage);
        return carImageRepository.save(carImage);
    }

    /**
     * Partially update a carImage.
     *
     * @param carImage the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<CarImage> partialUpdate(CarImage carImage) {
        log.debug("Request to partially update CarImage : {}", carImage);

        return carImageRepository
            .findById(carImage.getId())
            .map(existingCarImage -> {
                if (carImage.getImageDescription() != null) {
                    existingCarImage.setImageDescription(carImage.getImageDescription());
                }
                if (carImage.getCarImageUrl() != null) {
                    existingCarImage.setCarImageUrl(carImage.getCarImageUrl());
                }

                return existingCarImage;
            })
            .map(carImageRepository::save);
    }

    /**
     * Get all the carImages.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<CarImage> findAll() {
        log.debug("Request to get all CarImages");
        return carImageRepository.findAll();
    }

    /**
     * Get all the carImages with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<CarImage> findAllWithEagerRelationships(Pageable pageable) {
        return carImageRepository.findAllWithEagerRelationships(pageable);
    }

    /**
     * Get one carImage by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CarImage> findOne(Long id) {
        log.debug("Request to get CarImage : {}", id);
        return carImageRepository.findOneWithEagerRelationships(id);
    }

    /**
     * Delete the carImage by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CarImage : {}", id);
        carImageRepository.deleteById(id);
    }
}
