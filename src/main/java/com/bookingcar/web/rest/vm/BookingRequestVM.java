package com.bookingcar.web.rest.vm;

import java.time.LocalDate;

public class BookingRequestVM {
    private Long carId;
    private LocalDate date;
    private int slot;

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }
}
