package com.bookingcar.repository;

import com.bookingcar.domain.GroupAttribute;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the GroupAttribute entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GroupAttributeRepository extends JpaRepository<GroupAttribute, Long> {}
