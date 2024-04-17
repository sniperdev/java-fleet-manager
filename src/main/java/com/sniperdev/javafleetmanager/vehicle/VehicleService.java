package com.sniperdev.javafleetmanager.vehicle;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {
    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public List<Vehicle> getVehicles() {
        return vehicleRepository.findAll();
    }

    public Vehicle getVehicle(Long id) {
        return vehicleRepository.findById(id).orElse(null);
    }

    public Vehicle addVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public Vehicle updateVehicle(Vehicle vehicle, Long id) {
    Vehicle vehicleToUpdate = vehicleRepository.findById(id).orElse(null);
    if (vehicleToUpdate != null) {
        vehicleToUpdate.setBrand(vehicle.getBrand());
        vehicleToUpdate.setModel(vehicle.getModel());
        vehicleToUpdate.setYear(vehicle.getYear());
        vehicleToUpdate.setRegistrationNumber(vehicle.getRegistrationNumber());
        vehicleToUpdate.setType(vehicle.getType());
        vehicleToUpdate.setStatus(vehicle.getStatus());
        vehicleToUpdate.setDriverId(vehicle.getDriverId());
        vehicleRepository.save(vehicleToUpdate);
    }
    return vehicleRepository.findById(id).orElse(null);
}

    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id);
    }
}
