package com.bookingcar.web.rest.vm;

//{
//    "licensePlate": "string",
//    "price": 0,
//    "name": "string",
//    "status": "string",
//    "carattributes": [
//    {
//    "attributeName": "attributeValue": "string",
//    }
//    ],
//    "images": [
//    {
//    "imageDescription": "string",
//    "images": "file",
//    }
//    ],
//    "showroom": {
//    "id": 0,
//    },
//    "customer": {
//    "id": 0,
//    },
//    "carmodel": {
//    "id": 0,
//    }
//    }


import java.util.List;

public class CreateCarVM {
    private String licensePlate;
    private long price;

    private String name;
    private String status;
    private List<CarAttributePayload> carattributes;
    private long showroomId;
    private long customerId;
    private long carModelId;

    private long employeeId;

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
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

    public List<CarAttributePayload> getCarattributes() {
        return carattributes;
    }

    public void setCarattributes(List<CarAttributePayload> carattributes) {
        this.carattributes = carattributes;
    }

    public long getShowroomId() {
        return showroomId;
    }

    public void setShowroomId(long showroomId) {
        this.showroomId = showroomId;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public long getCarModelId() {
        return carModelId;
    }

    public void setCarModelId(long carModelId) {
        this.carModelId = carModelId;
    }
}
