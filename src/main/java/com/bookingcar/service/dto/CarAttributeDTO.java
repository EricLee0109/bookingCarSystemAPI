package com.bookingcar.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.bookingcar.domain.CarAttribute} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CarAttributeDTO implements Serializable {

    private Long id;

    @NotNull
    private String attributeValue;

    private AttributeDTO attribute;

    private CarDTO car;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }

    public AttributeDTO getAttribute() {
        return attribute;
    }

    public void setAttribute(AttributeDTO attribute) {
        this.attribute = attribute;
    }

    public CarDTO getCar() {
        return car;
    }

    public void setCar(CarDTO car) {
        this.car = car;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CarAttributeDTO)) {
            return false;
        }

        CarAttributeDTO carAttributeDTO = (CarAttributeDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, carAttributeDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CarAttributeDTO{" +
            "id=" + getId() +
            ", attributeValue='" + getAttributeValue() + "'" +
            ", attribute=" + getAttribute() +
            ", car=" + getCar() +
            "}";
    }
}
