package com.bookingcar.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.bookingcar.domain.ShowRoom} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ShowRoomDTO implements Serializable {

    private Long id;

    @NotNull
    private String address;

    @NotNull
    private String area;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ShowRoomDTO)) {
            return false;
        }

        ShowRoomDTO showRoomDTO = (ShowRoomDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, showRoomDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ShowRoomDTO{" +
            "id=" + getId() +
            ", address='" + getAddress() + "'" +
            ", area='" + getArea() + "'" +
            "}";
    }
}
