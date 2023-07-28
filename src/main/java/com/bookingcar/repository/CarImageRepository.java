package com.bookingcar.repository;

import com.bookingcar.domain.CarImage;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the CarImage entity.
 */
@Repository
public interface CarImageRepository extends JpaRepository<CarImage, Long> {
    default Optional<CarImage> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<CarImage> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<CarImage> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select distinct carImage from CarImage carImage left join fetch carImage.car",
        countQuery = "select count(distinct carImage) from CarImage carImage"
    )
    Page<CarImage> findAllWithToOneRelationships(Pageable pageable);

    @Query("select distinct carImage from CarImage carImage left join fetch carImage.car")
    List<CarImage> findAllWithToOneRelationships();

    @Query("select carImage from CarImage carImage left join fetch carImage.car where carImage.id =:id")
    Optional<CarImage> findOneWithToOneRelationships(@Param("id") Long id);
}
