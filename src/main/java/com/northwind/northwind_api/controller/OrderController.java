package com.northwind.northwind_api.controller;


import com.northwind.northwind_api.model.Order;
import com.northwind.northwind_api.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }
    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long orderId) {
        // Since your service returns an Optional<Order>, we unwrap it here
        Order order = orderService.getOrderById(orderId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found with id: " + orderId));
        return ResponseEntity.ok(order);
    }
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Order>> getOrderByCustomerId(@PathVariable String customerId) {
        List<Order> orders = orderService.getOrderByCustomerId(customerId);
        return ResponseEntity.ok(orders);
    }
    @GetMapping("/search")
    public ResponseEntity<List<Order>> getOrderByShipName(@RequestParam String shipName) {
        List<Order> orders = orderService.getOrderByShipName(shipName);
        return ResponseEntity.ok(orders);
    }
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order savedOrder = orderService.createOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedOrder);
    }
    @PutMapping("/{orderId}")
    public ResponseEntity<Order> changeOrder(@PathVariable Long orderId, @RequestBody Order updatedOrder) {
        Order order = orderService.changeOrder(orderId, updatedOrder)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to update order"));
        return ResponseEntity.ok(order);
    }
    @DeleteMapping("/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelOrder(@PathVariable Long orderId) {
        orderService.cancelOrder(orderId);
    }
}
