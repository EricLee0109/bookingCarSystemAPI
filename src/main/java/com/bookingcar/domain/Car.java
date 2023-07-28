package com.bookingcar.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A Car.
 */
@Entity
@Table(name = "car")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Car implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "license_plate", nullable = false)
    private String licensePlate;

    @NotNull
    @Column(name = "price", nullable = false)
    private Long price;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "status", nullable = false)
    private String status;

    @OneToMany(mappedBy = "car", fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value = { "car" }, allowSetters = true)
    private Set<CarAttribute> carattributes = new HashSet<>();

    @OneToMany(mappedBy = "car", fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value = { "car" }, allowSetters = true)
    private Set<CarImage> images = new HashSet<>();

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = { "cars", "employees" }, allowSetters = true)
    private ShowRoom showroom;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = { "cars" }, allowSetters = true)
    private Customer customer;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = { "cars", "showroom" }, allowSetters = true)
    private Employee employee;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = { "cars"}, allowSetters = true)
    private CarModel carmodel;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Car id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLicensePlate() {
        return this.licensePlate;
    }

    public Car licensePlate(String licensePlate) {
        this.setLicensePlate(licensePlate);
        return this;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Long getPrice() {
        return this.price;
    }

    public Car price(Long price) {
        this.setPrice(price);
        return this;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getName() {
        return this.name;
    }

    public Car name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return this.status;
    }

    public Car status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<CarAttribute> getCarattributes() {
        return this.carattributes;
    }

    public void setCarattributes(Set<CarAttribute> carAttributes) {
        if (this.carattributes != null) {
            this.carattributes.forEach(i -> i.setCar(null));
        }
        if (carAttributes != null) {
            carAttributes.forEach(i -> i.setCar(this));
        }
        this.carattributes = carAttributes;
    }

    public Car carattributes(Set<CarAttribute> carAttributes) {
        this.setCarattributes(carAttributes);
        return this;
    }

    public Car addCarattribute(CarAttribute carAttribute) {
        this.carattributes.add(carAttribute);
        carAttribute.setCar(this);
        return this;
    }

    public Car removeCarattribute(CarAttribute carAttribute) {
        this.carattributes.remove(carAttribute);
        carAttribute.setCar(null);
        return this;
    }

    public Set<CarImage> getImages() {
        return this.images;
    }

    public void setImages(Set<CarImage> carImages) {
        if (this.images != null) {
            this.images.forEach(i -> i.setCar(null));
        }
        if (carImages != null) {
            carImages.forEach(i -> i.setCar(this));
        }
        this.images = carImages;
    }

    public Car images(Set<CarImage> carImages) {
        this.setImages(carImages);
        return this;
    }

    public Car addImage(CarImage carImage) {
        this.images.add(carImage);
        carImage.setCar(this);
        return this;
    }

    public Car removeImage(CarImage carImage) {
        this.images.remove(carImage);
        carImage.setCar(null);
        return this;
    }

    public ShowRoom getShowroom() {
        return this.showroom;
    }

    public void setShowroom(ShowRoom showRoom) {
        this.showroom = showRoom;
    }

    public Car showroom(ShowRoom showRoom) {
        this.setShowroom(showRoom);
        return this;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Car customer(Customer customer) {
        this.setCustomer(customer);
        return this;
    }

    public Employee getEmployee() {
        return this.employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Car employee(Employee employee) {
        this.setEmployee(employee);
        return this;
    }

    public CarModel getCarmodel() {
        return this.carmodel;
    }

    public void setCarmodel(CarModel carModel) {
        this.carmodel = carModel;
    }

    public Car carmodel(CarModel carModel) {
        this.setCarmodel(carModel);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Car)) {
            return false;
        }
        return id != null && id.equals(((Car) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Car{" +
            "id=" + getId() +
            ", price=" + getPrice() +
            ", name='" + getName() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
