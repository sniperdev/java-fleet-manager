package com.sniperdev.javafleetmanager.vehicle;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "vehicles")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Brand is mandatory")
    @Size(min = 3, message = "Brand is too short")
    @Size(max = 15, message = "Brand is too long")
    private String brand;

    @Size(min = 3, message = "Model is too short")
    @Size(max = 15, message = "Model is too long")
    @NotBlank(message = "Model is mandatory")
    private String model;

    @NotBlank(message = "Year is mandatory")
    @Pattern(regexp = "[0-9]{4}")
    private String year;

    @NotBlank(message = "Registration number is mandatory")
    @Pattern(regexp = "([A-Z]{2}[0-9A-Z]{5})|([A-Z]{3}[0-9A-Z]{4})|([A-Z]{3}[0-9A-Z]{5})")
    private String registrationNumber;

    @NotBlank(message = "Type is mandatory")
    @Pattern(regexp = "(Van|Truck)")
    private String type;

    @NotBlank(message = "Status is mandatory")
    @Pattern(regexp = "(Available|In use|During technical work)")
    private String status;

    @NotBlank(message = "Driver ID is mandatory")
    @Pattern(regexp = "[0-9]+")
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
