package com.sniperdev.javafleetmanager.order;

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
@RequestMapping(path = "/orders")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<Order> getOrders() {
        return orderService.getOrders();
    }

    @GetMapping(path = "/{id}")
    public Order getOrder(@PathVariable Long id) {
        return orderService.getOrder(id);
    }

    @PostMapping(path="/add")
    public ResponseEntity<Object> addOrder(@Valid @RequestBody Order order) {
        Order addedOrder = orderService.addOrder(order);
        if(addedOrder == null) return ResponseEntity.notFound().build();
        else {
            URI location = URI.create("http://localhost:8080/orders/" + addedOrder.getId());
            return ResponseEntity.created(location).body(addedOrder);
        }
    }

    @PutMapping(path="/update/{id}")
    public ResponseEntity<Object> updateOrder(@Valid @RequestBody Order order, @PathVariable Long id) {
        Order updatedOrder = orderService.updateOrder(order, id);
        if(updatedOrder == null) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(updatedOrder);
    }

    @DeleteMapping(path="/delete/{id}")
    public ResponseEntity<String>  deleteVehicle(@PathVariable Long id) {
        orderService.deleteVehicle(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/list/download/pdf", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<org.springframework.core.io.Resource> generatePdfReport() {
        List<Order> orders = orderService.getOrders();
        ByteArrayInputStream bis = PdfGenerator.ordersReport(orders);
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
