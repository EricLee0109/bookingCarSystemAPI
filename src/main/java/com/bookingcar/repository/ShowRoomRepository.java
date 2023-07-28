package com.bookingcar.repository;

import com.bookingcar.domain.ShowRoom;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ShowRoom entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ShowRoomRepository extends JpaRepository<ShowRoom, Long> {}
