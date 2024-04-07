package com.sniperdev.javafleetmanager.driver;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverService {
    private final DriverRepository driverRepository;

    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    public List<Driver> getDrivers() {
        return driverRepository.findAll();
    }

    public Driver getDriver(Long id) {
        return driverRepository.findById(id).orElse(null);
    }

    public void addDriver(Driver driver) {
        driverRepository.save(driver);
    }

    public void updateDriver(Driver driver, Long id) {
        Driver driverToUpdate = driverRepository.findById(id).orElse(null);
        if (driverToUpdate != null) {
            driverToUpdate.setFirstName(driver.getFirstName());
            driverToUpdate.setLastName(driver.getLastName());
            driverToUpdate.setPhoneNumber(driver.getPhoneNumber());
            driverToUpdate.setEmail(driver.getEmail());
            driverToUpdate.setBirthDate(driver.getBirthDate());
            driverToUpdate.setDrivingLicenseNumber(driver.getDrivingLicenseNumber());
            driverRepository.save(driverToUpdate);
        }
    }

    public void deleteDriver(Long id) {
        driverRepository.deleteById(id);
    }
}
