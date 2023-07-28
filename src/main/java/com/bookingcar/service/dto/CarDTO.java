package com.bookingcar.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.bookingcar.domain.Car} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CarDTO implements Serializable {

    private Long id;

    @NotNull
    private Long price;

    @NotNull
    private String name;

    @NotNull
    private String status;

    private ShowRoomDTO showroom;

    private CustomerDTO customer;

    private CarModelDTO carmodel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ShowRoomDTO getShowroom() {
        return showroom;
    }

    public void setShowroom(ShowRoomDTO showroom) {
        this.showroom = showroom;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public CarModelDTO getCarmodel() {
        return carmodel;
    }

    public void setCarmodel(CarModelDTO carmodel) {
        this.carmodel = carmodel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CarDTO)) {
            return false;
        }

        CarDTO carDTO = (CarDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, carDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CarDTO{" +
            "id=" + getId() +
            ", price=" + getPrice() +
            ", name='" + getName() + "'" +
            ", status='" + getStatus() + "'" +
            ", showroom=" + getShowroom() +
            ", customer=" + getCustomer() +
            ", carmodel=" + getCarmodel() +
            "}";
    }
}
