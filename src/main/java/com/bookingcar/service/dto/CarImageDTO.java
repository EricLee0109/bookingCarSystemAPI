package com.bookingcar.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.bookingcar.domain.CarImage} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CarImageDTO implements Serializable {

    private Long id;

    @NotNull
    private String imageDescription;

    @NotNull
    private String carImageUrl;

    private CarDTO car;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageDescription() {
        return imageDescription;
    }

    public void setImageDescription(String imageDescription) {
        this.imageDescription = imageDescription;
    }

    public String getCarImageUrl() {
        return carImageUrl;
    }

    public void setCarImageUrl(String carImageUrl) {
        this.carImageUrl = carImageUrl;
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
        if (!(o instanceof CarImageDTO)) {
            return false;
        }

        CarImageDTO carImageDTO = (CarImageDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, carImageDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CarImageDTO{" +
            "id=" + getId() +
            ", imageDescription='" + getImageDescription() + "'" +
            ", carImageUrl='" + getCarImageUrl() + "'" +
            ", car=" + getCar() +
            "}";
    }
}
