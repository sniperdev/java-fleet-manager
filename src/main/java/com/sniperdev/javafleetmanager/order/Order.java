package com.sniperdev.javafleetmanager.order;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Pickup location is mandatory")
    private String pickupLocation;

    @NotBlank(message = "Delivery location is mandatory")
    private String deliveryLocation;

    @NotBlank(message = "Cargo description is mandatory")
    private String cargoDescription;

    @NotBlank(message = "Pickup time is mandatory")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}Z")
    private String pickupTime;

    @NotBlank(message = "Delivery time is mandatory")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}Z")
    private String deliveryTime;

    @NotBlank(message = "Vehicle id is mandatory")
    @Pattern(regexp = "[0-9]")
    private String vehicleId;

    @NotBlank(message = "Driver id is mandatory")
    @Pattern(regexp = "[0-9]")
    private String driverId;

    @NotBlank(message = "Status is mandatory")
    @Pattern(regexp = "(Pending|In progress|Completed)")
    private String status;

    public Order(Long id, String pickupLocation, String deliveryLocation, String cargoDescription, String pickupTime, String deliveryTime, String vehicleId, String driverId, String status) {
        this.id = id;
        this.pickupLocation = pickupLocation;
        this.deliveryLocation = deliveryLocation;
        this.cargoDescription = cargoDescription;
        this.pickupTime = pickupTime;
        this.deliveryTime = deliveryTime;
        this.vehicleId = vehicleId;
        this.driverId = driverId;
        this.status = status;
    }

    public Order() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public String getDeliveryLocation() {
        return deliveryLocation;
    }

    public void setDeliveryLocation(String deliveryLocation) {
        this.deliveryLocation = deliveryLocation;
    }

    public String getCargoDescription() {
        return cargoDescription;
    }

    public void setCargoDescription(String cargoDescription) {
        this.cargoDescription = cargoDescription;
    }

    public String getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(String pickupTime) {
        this.pickupTime = pickupTime;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
