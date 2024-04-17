package com.sniperdev.javafleetmanager.order;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public Order getOrder(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public Order addOrder(Order order) {
       return orderRepository.save(order);
    }

    public Order updateOrder(Order order, Long id) {
        Order orderToUpdate = orderRepository.findById(id).orElse(null);
        if (orderToUpdate != null) {
            orderToUpdate.setPickupLocation(order.getPickupLocation());
            orderToUpdate.setDeliveryLocation(order.getDeliveryLocation());
            orderToUpdate.setCargoDescription(order.getCargoDescription());
            orderToUpdate.setPickupTime(order.getPickupTime());
            orderToUpdate.setDeliveryTime(order.getDeliveryTime());
            orderToUpdate.setVehicleId(order.getVehicleId());
            orderToUpdate.setDriverId(order.getDriverId());
            orderToUpdate.setStatus(order.getStatus());
            orderRepository.save(orderToUpdate);
        }
        return orderRepository.findById(id).orElse(null);
    }

    public void deleteVehicle(Long id) {
        orderRepository.deleteById(id);
    }
}
