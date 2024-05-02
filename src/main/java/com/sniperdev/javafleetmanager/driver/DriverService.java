package com.sniperdev.javafleetmanager.driver;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public Driver addDriver(Driver driver) {
        return driverRepository.save(driver);
    }

    public Driver updateDriver(Driver driver, Long id) {
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
        return driverRepository.findById(id).orElse(null);
    }

    public void deleteDriver(Long id) {
        driverRepository.deleteById(id);
    }

    public List<DriverInfo> getDriverInfo() {
        return driverRepository.findAll().stream()
                .map(driver -> new DriverInfo(driver.getId(), driver.getFirstName(), driver.getLastName()))
                .collect(Collectors.toList());
    }
}
