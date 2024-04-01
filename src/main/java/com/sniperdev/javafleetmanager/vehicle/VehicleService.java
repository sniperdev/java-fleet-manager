package com.sniperdev.javafleetmanager.vehicle;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleService {
    List<Vehicle> vehicleList = new ArrayList<>();

    @PostConstruct
    public void init() {
        vehicleList.add(new Vehicle(1L, "Toyota", "Corolla", "2015", "KBC 123", "Saloon", "Available", "1"));
        vehicleList.add(new Vehicle(2L, "Toyota", "Vitz", "2018", "KBC 456", "Hatchback", "Available", "2"));
        vehicleList.add(new Vehicle(3L, "Toyota", "Land Cruiser", "2019", "KBC 789", "SUV", "Available", "3"));
    }

    public List<Vehicle> getVehicles() {
        return vehicleList;
    }

    public Vehicle getVehicle(Long id) {
        return vehicleList.stream().filter(vehicle -> vehicle.getId().equals(id)).findFirst().orElse(null);
    }

    public void addVehicle(Vehicle vehicle) {
        vehicle.setId(vehicleList.getLast().getId() + 1L);
        vehicleList.add(vehicle);
    }

    public void updateVehicle(Vehicle vehicle, Long id) {
        Vehicle vehicleToUpdate = getVehicle(id);
        if (vehicleToUpdate != null) {
            vehicleToUpdate.setBrand(vehicle.getBrand());
            vehicleToUpdate.setModel(vehicle.getModel());
            vehicleToUpdate.setYear(vehicle.getYear());
            vehicleToUpdate.setRegistrationNumber(vehicle.getRegistrationNumber());
            vehicleToUpdate.setType(vehicle.getType());
            vehicleToUpdate.setStatus(vehicle.getStatus());
            vehicleToUpdate.setDriverId(vehicle.getDriverId());
        }
    }

    public void deleteVehicle(Long id) {
        vehicleList.removeIf(vehicle -> vehicle.getId().equals(id));
    }
}
