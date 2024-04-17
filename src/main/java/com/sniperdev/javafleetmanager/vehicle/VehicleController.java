package com.sniperdev.javafleetmanager.vehicle;

import com.sniperdev.javafleetmanager.utils.PdfGenerator;
import jakarta.validation.Valid;

import java.io.ByteArrayInputStream;
import java.net.URI;
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
    public ResponseEntity<Object> addVehicle(@Valid @RequestBody Vehicle vehicle) {
        Vehicle addeddVehicle = vehicleService.addVehicle(vehicle);
        if (addeddVehicle == null) return ResponseEntity.notFound().build();
        else {
            URI location = URI.create("http://localhost:8080/vehicles/" + addeddVehicle.getId());
            return ResponseEntity.created(location).body(addeddVehicle);
        }
    }

    @PutMapping(path="/update/{id}")
    public ResponseEntity<Object> updateVehicle(@Valid @RequestBody Vehicle vehicle, @PathVariable Long id) {
        Vehicle updatedVehicle = vehicleService.updateVehicle(vehicle, id);
        if (updatedVehicle == null) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(updatedVehicle);
    }

    @DeleteMapping(path="/delete/{id}")
    public ResponseEntity<String> deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/list/download/pdf", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
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