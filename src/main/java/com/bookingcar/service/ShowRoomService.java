package com.bookingcar.service;

import com.bookingcar.domain.ShowRoom;
import com.bookingcar.repository.ShowRoomRepository;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ShowRoom}.
 */
@Service
@Transactional
public class ShowRoomService {

    private final Logger log = LoggerFactory.getLogger(ShowRoomService.class);

    private final ShowRoomRepository showRoomRepository;

    public ShowRoomService(ShowRoomRepository showRoomRepository) {
        this.showRoomRepository = showRoomRepository;
    }

    /**
     * Save a showRoom.
     *
     * @param showRoom the entity to save.
     * @return the persisted entity.
     */
    public ShowRoom save(ShowRoom showRoom) {
        log.debug("Request to save ShowRoom : {}", showRoom);
        return showRoomRepository.save(showRoom);
    }

    /**
     * Update a showRoom.
     *
     * @param showRoom the entity to save.
     * @return the persisted entity.
     */
    public ShowRoom update(ShowRoom showRoom) {
        log.debug("Request to update ShowRoom : {}", showRoom);
        return showRoomRepository.save(showRoom);
    }

    /**
     * Partially update a showRoom.
     *
     * @param showRoom the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ShowRoom> partialUpdate(ShowRoom showRoom) {
        log.debug("Request to partially update ShowRoom : {}", showRoom);

        return showRoomRepository
            .findById(showRoom.getId())
            .map(existingShowRoom -> {
                if (showRoom.getAddress() != null) {
                    existingShowRoom.setAddress(showRoom.getAddress());
                }
                if (showRoom.getArea() != null) {
                    existingShowRoom.setArea(showRoom.getArea());
                }

                return existingShowRoom;
            })
            .map(showRoomRepository::save);
    }

    /**
     * Get all the showRooms.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ShowRoom> findAll() {
        log.debug("Request to get all ShowRooms");
        return showRoomRepository.findAll();
    }

    /**
     * Get one showRoom by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ShowRoom> findOne(Long id) {
        log.debug("Request to get ShowRoom : {}", id);
        return showRoomRepository.findById(id);
    }

    /**
     * Delete the showRoom by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ShowRoom : {}", id);
        showRoomRepository.deleteById(id);
    }
}
