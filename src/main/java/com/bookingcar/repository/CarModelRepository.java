package com.bookingcar.repository;

import com.bookingcar.domain.CarModel;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the CarModel entity.
 */
@Repository
public interface CarModelRepository extends JpaRepository<CarModel, Long> {
    default Optional<CarModel> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<CarModel> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<CarModel> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select distinct carModel from CarModel carModel left join fetch carModel.brand",
        countQuery = "select count(distinct carModel) from CarModel carModel"
    )
    Page<CarModel> findAllWithToOneRelationships(Pageable pageable);

    @Query("select distinct carModel from CarModel carModel left join fetch carModel.brand")
    List<CarModel> findAllWithToOneRelationships();

    @Query("select carModel from CarModel carModel left join fetch carModel.brand where carModel.id =:id")
    Optional<CarModel> findOneWithToOneRelationships(@Param("id") Long id);
}
