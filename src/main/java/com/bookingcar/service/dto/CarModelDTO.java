package com.bookingcar.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.bookingcar.domain.CarModel} entity.
 */
@Schema(description = "Task entity.\n@author The JHipster team.")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CarModelDTO implements Serializable {

    private Long id;

    @NotNull
    private String carModelName;

    @NotNull
    private String type;

    @NotNull
    private LocalDate yearOfManufacture;

    private BrandDTO brand;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCarModelName() {
        return carModelName;
    }

    public void setCarModelName(String carModelName) {
        this.carModelName = carModelName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getYearOfManufacture() {
        return yearOfManufacture;
    }

    public void setYearOfManufacture(LocalDate yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
    }

    public BrandDTO getBrand() {
        return brand;
    }

    public void setBrand(BrandDTO brand) {
        this.brand = brand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CarModelDTO)) {
            return false;
        }

        CarModelDTO carModelDTO = (CarModelDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, carModelDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CarModelDTO{" +
            "id=" + getId() +
            ", carModelName='" + getCarModelName() + "'" +
            ", type='" + getType() + "'" +
            ", yearOfManufacture='" + getYearOfManufacture() + "'" +
            ", brand=" + getBrand() +
            "}";
    }
}
