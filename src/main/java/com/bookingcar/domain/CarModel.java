package com.bookingcar.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * Task entity.\n@author The JHipster team.
 */
@Schema(description = "Task entity.\n@author The JHipster team.")
@Entity
@Table(name = "car_model")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CarModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "car_model_name", nullable = false)
    private String carModelName;

    @NotNull
    @Column(name = "type", nullable = false)
    private String type;

    @NotNull
    @Column(name = "year_of_manufacture", nullable = false)
    private LocalDate yearOfManufacture;

    @OneToMany(mappedBy = "carmodel")
    @JsonIgnoreProperties(value = { "carattributes", "images", "showroom", "customer", "employee", "carmodel" }, allowSetters = true)
    private Set<Car> cars = new HashSet<>();

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @NotNull
    @JsonIgnoreProperties(value = { "carmodels" }, allowSetters = true)
    private Brand brand;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public CarModel id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCarModelName() {
        return this.carModelName;
    }

    public CarModel carModelName(String carModelName) {
        this.setCarModelName(carModelName);
        return this;
    }

    public void setCarModelName(String carModelName) {
        this.carModelName = carModelName;
    }

    public String getType() {
        return this.type;
    }

    public CarModel type(String type) {
        this.setType(type);
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getYearOfManufacture() {
        return this.yearOfManufacture;
    }

    public CarModel yearOfManufacture(LocalDate yearOfManufacture) {
        this.setYearOfManufacture(yearOfManufacture);
        return this;
    }

    public void setYearOfManufacture(LocalDate yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
    }

    public Set<Car> getCars() {
        return this.cars;
    }

    public void setCars(Set<Car> cars) {
        if (this.cars != null) {
            this.cars.forEach(i -> i.setCarmodel(null));
        }
        if (cars != null) {
            cars.forEach(i -> i.setCarmodel(this));
        }
        this.cars = cars;
    }

    public CarModel cars(Set<Car> cars) {
        this.setCars(cars);
        return this;
    }

    public CarModel addCar(Car car) {
        this.cars.add(car);
        car.setCarmodel(this);
        return this;
    }

    public CarModel removeCar(Car car) {
        this.cars.remove(car);
        car.setCarmodel(null);
        return this;
    }

    public Brand getBrand() {
        return this.brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public CarModel brand(Brand brand) {
        this.setBrand(brand);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CarModel)) {
            return false;
        }
        return id != null && id.equals(((CarModel) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CarModel{" +
            "id=" + getId() +
            ", carModelName='" + getCarModelName() + "'" +
            ", type='" + getType() + "'" +
            ", yearOfManufacture='" + getYearOfManufacture() + "'" +
            "}";
    }
}
