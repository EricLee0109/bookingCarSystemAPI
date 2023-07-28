package com.bookingcar.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A Customer.
 */
@Entity
@Table(name = "customer")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "phone", nullable = false)
    private String phone;


    @OneToOne(optional = false, fetch = FetchType.EAGER)
    @NotNull
    @JoinColumn(unique = true)
    private User user;

    @OneToMany(mappedBy = "customer")
    @JsonIgnoreProperties(value = { "carattributes", "images", "showroom", "customer", "employee", "carmodel" }, allowSetters = true)
    private Set<Car> cars = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Customer id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return this.phone;
    }

    public Customer phone(String phone) {
        this.setPhone(phone);
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Customer user(User user) {
        this.setUser(user);
        return this;
    }

    public Set<Car> getCars() {
        return this.cars;
    }

    public void setCars(Set<Car> cars) {
        if (this.cars != null) {
            this.cars.forEach(i -> i.setCustomer(null));
        }
        if (cars != null) {
            cars.forEach(i -> i.setCustomer(this));
        }
        this.cars = cars;
    }

    public Customer cars(Set<Car> cars) {
        this.setCars(cars);
        return this;
    }

    public Customer addCar(Car car) {
        this.cars.add(car);
        car.setCustomer(this);
        return this;
    }

    public Customer removeCar(Car car) {
        this.cars.remove(car);
        car.setCustomer(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Customer)) {
            return false;
        }
        return id != null && id.equals(((Customer) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Customer{" +
            "id=" + getId() +
            ", phone='" + getPhone() + "'" +
            "}";
    }
}
