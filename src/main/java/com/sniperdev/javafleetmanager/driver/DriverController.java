package com.sniperdev.javafleetmanager.driver;

import com.sniperdev.javafleetmanager.utils.PdfGenerator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/drivers")
public class DriverController {
    private final DriverService driverService;
    @Autowired
    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @GetMapping
    public List<Driver> getDrivers() {
        return driverService.getDrivers();
    }

    @GetMapping(path = "/{id}")
    public Driver getDriver(@PathVariable @Valid Long id) {
        return driverService.getDriver(id);
    }

    @PostMapping(path = "/add")
    public ResponseEntity<Object> addDriver(@RequestBody @Valid Driver driver) {
        Driver addedDriver = driverService.addDriver(driver);
        if(addedDriver == null) return ResponseEntity.notFound().build();
        else{
            URI location = URI.create("http://localhost:8080/drivers/" + addedDriver.getId());
            return ResponseEntity.created(location).body(addedDriver);
        }
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<Object> updateDriver(@RequestBody @Valid Driver driver, @PathVariable @Valid Long id) {
        Driver updatedDriver = driverService.updateDriver(driver, id);
        if(updatedDriver == null) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(updatedDriver);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String> deleteDriver(@PathVariable @Valid Long id) {
        driverService.deleteDriver(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/list/download/pdf", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<org.springframework.core.io.Resource> generatePdfReport() {
        List<Driver> drivers = driverService.getDrivers();
        ByteArrayInputStream bis = PdfGenerator.driversReport(drivers);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=vehicles.pdf");
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

    @GetMapping(path = "/dictionaries")
    public List<DriverInfo> getDriverInfo() {
        return driverService.getDriverInfo();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
