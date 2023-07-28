package com.bookingcar.service;

import com.bookingcar.domain.Car;
import com.bookingcar.repository.CarModelRepository;
import com.bookingcar.repository.CarRepository;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Car}.
 */
@Service
@Transactional
public class CarService {

    private final Logger log = LoggerFactory.getLogger(CarService.class);

    private final CarRepository carRepository;

    private final CarModelRepository carModelRepository;

    public CarService(CarRepository carRepository, CarModelRepository carModelRepository) {
        this.carRepository = carRepository;
        this.carModelRepository = carModelRepository;
    }

    /**
     * Save a car.
     *
     * @param car the entity to save.
     * @return the persisted entity.
     */
    public Car save(Car car) {
        log.debug("Request to save Car : {}", car);
        return carRepository.save(car);
    }

    /**
     * Update a car.
     *
     * @param car the entity to save.
     * @return the persisted entity.
     */
    public Car update(Car car) {
        log.debug("Request to update Car : {}", car);
        return carRepository.save(car);
    }

    /**
     * Partially update a car.
     *
     * @param car the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<Car> partialUpdate(Car car) {
        log.debug("Request to partially update Car : {}", car);

        return carRepository
            .findById(car.getId())
            .map(existingCar -> {
                if (car.getPrice() != null) {
                    existingCar.setPrice(car.getPrice());
                }
                if (car.getName() != null) {
                    existingCar.setName(car.getName());
                }
                if (car.getStatus() != null) {
                    existingCar.setStatus(car.getStatus());
                }

                return existingCar;
            })
            .map(carRepository::save);
    }

    /**
     * Get all the cars.
     *
     * @return the list of entities.
     */

    @Transactional(readOnly = true)
    public List<Car> findAll(String area, long minPrice, long maxPrice, long brandId) {
        log.debug("Request to get all Cars");
        return carRepository.findByShowroom_AreaContainingAndPriceBetweenAndCarmodel_Brand_Id(area, minPrice, maxPrice, brandId);
    }

    @Transactional(readOnly = true)
    public List<Car> findAllWithoutBrand(String area, long minPrice, long maxPrice) {
        log.debug("Request to get all Cars");
        return carRepository.findByShowroom_AreaContainingAndPriceBetween(area, minPrice, maxPrice);
    }

    @Transactional(readOnly = true)
    public List<Car> findWithCarModel(String area, long minPrice, long maxPrice, long brandId, long modelId) {
        log.debug("Request to get all Cars");
        return carRepository.findByShowroom_AreaContainingAndPriceBetweenAndCarmodel_Brand_IdAndCarmodel_Id(area, minPrice, maxPrice, brandId, modelId);
    }

    @Transactional(readOnly = true)
    public List<Car> findByCarmodel_Brand_Id(long brandId) {
        log.debug("Request to get all Cars");
        return carRepository.findByCarmodel_Brand_Id(brandId);
    }



    /**
     * Get one car by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Car> findOne(Long id) {
        log.debug("Request to get Car : {}", id);
        return carRepository.findById(id);
    }

    /**
     * Delete the car by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Car : {}", id);
        carRepository.deleteById(id);
    }
}
