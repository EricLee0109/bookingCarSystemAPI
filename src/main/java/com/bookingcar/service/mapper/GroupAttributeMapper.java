package com.bookingcar.service.mapper;

import com.bookingcar.domain.GroupAttribute;
import com.bookingcar.service.dto.GroupAttributeDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link GroupAttribute} and its DTO {@link GroupAttributeDTO}.
 */
@Mapper(componentModel = "spring")
public interface GroupAttributeMapper extends EntityMapper<GroupAttributeDTO, GroupAttribute> {}
