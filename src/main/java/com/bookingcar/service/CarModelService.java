package com.bookingcar.service;

import com.bookingcar.domain.CarModel;
import com.bookingcar.repository.CarModelRepository;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link CarModel}.
 */
@Service
@Transactional
public class CarModelService {

    private final Logger log = LoggerFactory.getLogger(CarModelService.class);

    private final CarModelRepository carModelRepository;

    public CarModelService(CarModelRepository carModelRepository) {
        this.carModelRepository = carModelRepository;
    }

    /**
     * Save a carModel.
     *
     * @param carModel the entity to save.
     * @return the persisted entity.
     */
    public CarModel save(CarModel carModel) {
        log.debug("Request to save CarModel : {}", carModel);
        return carModelRepository.save(carModel);
    }

    /**
     * Update a carModel.
     *
     * @param carModel the entity to save.
     * @return the persisted entity.
     */
    public CarModel update(CarModel carModel) {
        log.debug("Request to update CarModel : {}", carModel);
        return carModelRepository.save(carModel);
    }

    /**
     * Partially update a carModel.
     *
     * @param carModel the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<CarModel> partialUpdate(CarModel carModel) {
        log.debug("Request to partially update CarModel : {}", carModel);

        return carModelRepository
            .findById(carModel.getId())
            .map(existingCarModel -> {
                if (carModel.getCarModelName() != null) {
                    existingCarModel.setCarModelName(carModel.getCarModelName());
                }
                if (carModel.getType() != null) {
                    existingCarModel.setType(carModel.getType());
                }
                if (carModel.getYearOfManufacture() != null) {
                    existingCarModel.setYearOfManufacture(carModel.getYearOfManufacture());
                }

                return existingCarModel;
            })
            .map(carModelRepository::save);
    }

    /**
     * Get all the carModels.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<CarModel> findAll() {
        log.debug("Request to get all CarModels");
        return carModelRepository.findAll();
    }

    /**
     * Get all the carModels with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<CarModel> findAllWithEagerRelationships(Pageable pageable) {
        return carModelRepository.findAllWithEagerRelationships(pageable);
    }

    /**
     * Get one carModel by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CarModel> findOne(Long id) {
        log.debug("Request to get CarModel : {}", id);
        return carModelRepository.findOneWithEagerRelationships(id);
    }

    /**
     * Delete the carModel by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CarModel : {}", id);
        carModelRepository.deleteById(id);
    }
}
