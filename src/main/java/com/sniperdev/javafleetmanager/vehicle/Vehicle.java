package com.sniperdev.javafleetmanager.vehicle;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Vehicle {

    @Id
    private Long id;
    @NotBlank(message = "Brand is mandatory")
    private String brand;
    @NotBlank(message = "Model is mandatory")
    private String model;
    @NotBlank(message = "Year is mandatory")
    private String year;
    @NotBlank(message = "Registration number is mandatory")
    private String registrationNumber;
    @NotBlank(message = "Type is mandatory")
    private String type;
    @NotBlank(message = "Status is mandatory")
    private String status;
    @NotBlank(message = "Driver ID is mandatory")
    private String driverId;

    public Vehicle(Long id, String brand, String model, String year, String registrationNumber, String type, String status, String driverId) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.registrationNumber = registrationNumber;
        this.type = type;
        this.status = status;
        this.driverId = driverId;
    }

    public Vehicle() {

    }


    public Long getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getYear() {
        return year;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getType() {
        return type;
    }

    public String getStatus() {
        return status;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }
}
