package com.sniperdev.javafleetmanager.vehicle;

import com.sniperdev.javafleetmanager.utils.PdfGenerator;
import jakarta.validation.Valid;

import java.io.ByteArrayInputStream;
import java.util.Map;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping
    public List<Vehicle> getVehicles() {
        return vehicleService.getVehicles();
    }

    @GetMapping(path = "/{id}")
    public Vehicle getVehicle(@PathVariable Long id) {
        return vehicleService.getVehicle(id);
    }

    @PostMapping(path="/add")
    public ResponseEntity<String> addVehicle(@Valid @RequestBody Vehicle vehicle) {
        vehicleService.addVehicle(vehicle);
        return ResponseEntity.ok("Vehicle added successfully");
    }

    @PutMapping(path="/update/{id}")
    public String updateVehicle(@Valid @RequestBody Vehicle vehicle, @PathVariable Long id) {
        vehicleService.updateVehicle(vehicle, id);
        return "Vehicle updated successfully";
    }

    @DeleteMapping(path="/delete/{id}")
    public String deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
        return "Vehicle deleted successfully";
    }

    @RequestMapping(value = "/pdfreport", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<org.springframework.core.io.Resource> generatePdfReport() {
        List<Vehicle> vehicles = vehicleService.getVehicles();
        ByteArrayInputStream bis = PdfGenerator.vehiclesReport(vehicles);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=vehicles.pdf");
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
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