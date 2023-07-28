package com.bookingcar.repository;

import com.bookingcar.domain.CarAttribute;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the CarAttribute entity.
 */
@Repository
public interface CarAttributeRepository extends JpaRepository<CarAttribute, Long> {
    default Optional<CarAttribute> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<CarAttribute> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<CarAttribute> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select distinct carAttribute from CarAttribute carAttribute left join fetch carAttribute.attribute left join fetch carAttribute.car",
        countQuery = "select count(distinct carAttribute) from CarAttribute carAttribute"
    )
    Page<CarAttribute> findAllWithToOneRelationships(Pageable pageable);

    @Query(
        "select distinct carAttribute from CarAttribute carAttribute left join fetch carAttribute.attribute left join fetch carAttribute.car"
    )
    List<CarAttribute> findAllWithToOneRelationships();

    @Query(
        "select carAttribute from CarAttribute carAttribute left join fetch carAttribute.attribute left join fetch carAttribute.car where carAttribute.id =:id"
    )
    Optional<CarAttribute> findOneWithToOneRelationships(@Param("id") Long id);
}
