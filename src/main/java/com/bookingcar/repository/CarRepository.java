package com.bookingcar.repository;

import com.bookingcar.domain.Car;
import com.bookingcar.domain.Employee;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data JPA repository for the Car entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByShowroom_AreaContainingAndPriceBetweenAndCarmodel_Brand_Id(String area, long minPrice, long maxPrice, long brandId);

    List<Car> findByShowroom_AreaContainingAndPriceBetweenAndCarmodel_Brand_IdAndCarmodel_Id(String area, long minPrice, long maxPrice, long brandId, long modelId);

    List<Car> findByShowroom_AreaContainingAndPriceBetween(String area, long minPrice, long maxPrice);

    List<Car> findByCarmodel_Brand_Id(long brandId);


}
