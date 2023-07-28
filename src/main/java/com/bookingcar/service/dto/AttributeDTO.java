package com.bookingcar.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.bookingcar.domain.Attribute} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class AttributeDTO implements Serializable {

    private Long id;

    @NotNull
    private String attributeName;

    @NotNull
    private Long displayIndex;

    private GroupAttributeDTO groupattribute;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public Long getDisplayIndex() {
        return displayIndex;
    }

    public void setDisplayIndex(Long displayIndex) {
        this.displayIndex = displayIndex;
    }

    public GroupAttributeDTO getGroupattribute() {
        return groupattribute;
    }

    public void setGroupattribute(GroupAttributeDTO groupattribute) {
        this.groupattribute = groupattribute;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AttributeDTO)) {
            return false;
        }

        AttributeDTO attributeDTO = (AttributeDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, attributeDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AttributeDTO{" +
            "id=" + getId() +
            ", attributeName='" + getAttributeName() + "'" +
            ", displayIndex=" + getDisplayIndex() +
            ", groupattribute=" + getGroupattribute() +
            "}";
    }
}
