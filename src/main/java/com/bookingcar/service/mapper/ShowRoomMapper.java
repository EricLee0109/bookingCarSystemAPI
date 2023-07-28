package com.bookingcar.service.mapper;

import com.bookingcar.domain.ShowRoom;
import com.bookingcar.service.dto.ShowRoomDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ShowRoom} and its DTO {@link ShowRoomDTO}.
 */
@Mapper(componentModel = "spring")
public interface ShowRoomMapper extends EntityMapper<ShowRoomDTO, ShowRoom> {}
