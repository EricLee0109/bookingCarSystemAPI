package com.bookingcar.repository;

import com.bookingcar.domain.Attribute;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Attribute entity.
 */
@Repository
public interface AttributeRepository extends JpaRepository<Attribute, Long> {
    default Optional<Attribute> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<Attribute> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<Attribute> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select distinct attribute from Attribute attribute left join fetch attribute.groupattribute",
        countQuery = "select count(distinct attribute) from Attribute attribute"
    )
    Page<Attribute> findAllWithToOneRelationships(Pageable pageable);

    @Query("select distinct attribute from Attribute attribute left join fetch attribute.groupattribute")
    List<Attribute> findAllWithToOneRelationships();

    @Query("select attribute from Attribute attribute left join fetch attribute.groupattribute where attribute.id =:id")
    Optional<Attribute> findOneWithToOneRelationships(@Param("id") Long id);
}
