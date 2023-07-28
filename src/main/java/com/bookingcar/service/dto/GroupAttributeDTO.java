package com.bookingcar.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.bookingcar.domain.GroupAttribute} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class GroupAttributeDTO implements Serializable {

    private Long id;

    @NotNull
    private String groupAttributeName;

    @NotNull
    private Integer displayIndex;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupAttributeName() {
        return groupAttributeName;
    }

    public void setGroupAttributeName(String groupAttributeName) {
        this.groupAttributeName = groupAttributeName;
    }

    public Integer getDisplayIndex() {
        return displayIndex;
    }

    public void setDisplayIndex(Integer displayIndex) {
        this.displayIndex = displayIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GroupAttributeDTO)) {
            return false;
        }

        GroupAttributeDTO groupAttributeDTO = (GroupAttributeDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, groupAttributeDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GroupAttributeDTO{" +
            "id=" + getId() +
            ", groupAttributeName='" + getGroupAttributeName() + "'" +
            ", displayIndex=" + getDisplayIndex() +
            "}";
    }
}
