package com.bookingcar.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A ShowRoom.
 */
@Entity
@Table(name = "show_room")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ShowRoom implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "address", nullable = false)
    private String address;

    @NotNull
    @Column(name = "area", nullable = false)
    private String area;

    @OneToMany(mappedBy = "showroom")
    @JsonIgnoreProperties(value = { "carattributes", "images", "showroom", "customer", "employee", "carmodel" }, allowSetters = true)
    private Set<Car> cars = new HashSet<>();

    @OneToMany(mappedBy = "showroom")
    @JsonIgnoreProperties(value = { "cars", "showroom" }, allowSetters = true)
    private Set<Employee> employees = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ShowRoom id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return this.address;
    }

    public ShowRoom address(String address) {
        this.setAddress(address);
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getArea() {
        return this.area;
    }

    public ShowRoom area(String area) {
        this.setArea(area);
        return this;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Set<Car> getCars() {
        return this.cars;
    }

    public void setCars(Set<Car> cars) {
        if (this.cars != null) {
            this.cars.forEach(i -> i.setShowroom(null));
        }
        if (cars != null) {
            cars.forEach(i -> i.setShowroom(this));
        }
        this.cars = cars;
    }

    public ShowRoom cars(Set<Car> cars) {
        this.setCars(cars);
        return this;
    }

    public ShowRoom addCar(Car car) {
        this.cars.add(car);
        car.setShowroom(this);
        return this;
    }

    public ShowRoom removeCar(Car car) {
        this.cars.remove(car);
        car.setShowroom(null);
        return this;
    }

    public Set<Employee> getEmployees() {
        return this.employees;
    }

    public void setEmployees(Set<Employee> employees) {
        if (this.employees != null) {
            this.employees.forEach(i -> i.setShowroom(null));
        }
        if (employees != null) {
            employees.forEach(i -> i.setShowroom(this));
        }
        this.employees = employees;
    }

    public ShowRoom employees(Set<Employee> employees) {
        this.setEmployees(employees);
        return this;
    }

    public ShowRoom addEmployee(Employee employee) {
        this.employees.add(employee);
        employee.setShowroom(this);
        return this;
    }

    public ShowRoom removeEmployee(Employee employee) {
        this.employees.remove(employee);
        employee.setShowroom(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ShowRoom)) {
            return false;
        }
        return id != null && id.equals(((ShowRoom) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ShowRoom{" +
            "id=" + getId() +
            ", address='" + getAddress() + "'" +
            ", area='" + getArea() + "'" +
            "}";
    }
}
