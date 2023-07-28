package com.bookingcar.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A CarImage.
 */
@Entity
@Table(name = "car_image")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CarImage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "image_description", nullable = false)
    private String imageDescription;

    @NotNull
    @Column(name = "car_image_url", nullable = false)
    private String carImageUrl;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = { "carattributes", "images", "showroom", "customer", "employee", "carmodel" }, allowSetters = true)
    private Car car;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public CarImage id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageDescription() {
        return this.imageDescription;
    }

    public CarImage imageDescription(String imageDescription) {
        this.setImageDescription(imageDescription);
        return this;
    }

    public void setImageDescription(String imageDescription) {
        this.imageDescription = imageDescription;
    }

    public String getCarImageUrl() {
        return this.carImageUrl;
    }

    public CarImage carImageUrl(String carImageUrl) {
        this.setCarImageUrl(carImageUrl);
        return this;
    }

    public void setCarImageUrl(String carImageUrl) {
        this.carImageUrl = carImageUrl;
    }

    public Car getCar() {
        return this.car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public CarImage car(Car car) {
        this.setCar(car);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CarImage)) {
            return false;
        }
        return id != null && id.equals(((CarImage) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CarImage{" +
            "id=" + getId() +
            ", imageDescription='" + getImageDescription() + "'" +
            ", carImageUrl='" + getCarImageUrl() + "'" +
            "}";
    }
}
