package com.bookingcar.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A Employee.
 */
@Entity
@Table(name = "employee")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "phone", nullable = false)
    private String phone;

    @NotNull
    @Column(name = "gender", nullable = false)
    private String gender;

    @OneToOne(optional = false)
    @NotNull
    @JoinColumn(unique = true)
    private User user;

    @OneToMany(mappedBy = "employee")
    @JsonIgnoreProperties(value = { "carattributes", "images", "showroom", "customer", "employee", "carmodel" }, allowSetters = true)
    private Set<Car> cars = new HashSet<>();

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = { "cars", "employees" }, allowSetters = true)
    private ShowRoom showroom;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Employee id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return this.phone;
    }

    public Employee phone(String phone) {
        this.setPhone(phone);
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return this.gender;
    }

    public Employee gender(String gender) {
        this.setGender(gender);
        return this;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Employee user(User user) {
        this.setUser(user);
        return this;
    }

    public Set<Car> getCars() {
        return this.cars;
    }

    public void setCars(Set<Car> cars) {
        if (this.cars != null) {
            this.cars.forEach(i -> i.setEmployee(null));
        }
        if (cars != null) {
            cars.forEach(i -> i.setEmployee(this));
        }
        this.cars = cars;
    }

    public Employee cars(Set<Car> cars) {
        this.setCars(cars);
        return this;
    }

    public Employee addCar(Car car) {
        this.cars.add(car);
        car.setEmployee(this);
        return this;
    }

    public Employee removeCar(Car car) {
        this.cars.remove(car);
        car.setEmployee(null);
        return this;
    }

    public ShowRoom getShowroom() {
        return this.showroom;
    }

    public void setShowroom(ShowRoom showRoom) {
        this.showroom = showRoom;
    }

    public Employee showroom(ShowRoom showRoom) {
        this.setShowroom(showRoom);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Employee)) {
            return false;
        }
        return id != null && id.equals(((Employee) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Employee{" +
            "id=" + getId() +
            ", phone='" + getPhone() + "'" +
            ", gender='" + getGender() + "'" +
            "}";
    }
}
