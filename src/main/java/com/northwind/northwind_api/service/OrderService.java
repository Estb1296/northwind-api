package com.northwind.northwind_api.service;

import com.northwind.northwind_api.model.Order;
import com.northwind.northwind_api.repository.OrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

private final OrderRepository orderRepository;

public OrderService(OrderRepository orderRepository){
    this.orderRepository = orderRepository;
}

public List<Order> getAllOrders(){
    return orderRepository.findAllWithCustomer();
}
public Optional<Order> getOrderById(Long orderId){
    return orderRepository.findById(orderId);
}

public List<Order>getOrderByCustomerId(String customerId){
    List<Order>orders = orderRepository.findByCustomerCustomerId(customerId);
    if(orders.isEmpty()){
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Order can't be found with this cutomerId");
    }
        return orders;

}
    public List<Order>getOrderByShipName(String shipName){
    List<Order>orders = orderRepository.findByShipNameContainingIgnoreCase(shipName);
    if(orders.isEmpty()){
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Order can't be found with this ship name");
    }
    return orders;
    }

    public void cancelOrder(Long orderId) {
        orderRepository.findById(orderId)
                .ifPresentOrElse(
                        order -> orderRepository.deleteById(orderId),
                        ()-> {
                            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Can not find order to be deleted");
                        })
        ;
    }
    public Optional<Order> changeOrder(Long orderId, Order updatedOrder) {
        Order existing = orderRepository.findById(orderId)
                .map(order -> {
                    // Update in the map
                    order.setOrderDate(updatedOrder.getOrderDate());
                    order.setShippedDate(updatedOrder.getShippedDate());
                    order.setRequiredDate(updatedOrder.getRequiredDate());
                    return order;  // Return updated order
                })
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Order not found with id: " + orderId));

        return Optional.of(orderRepository.save(existing));
    }

    public Order createOrder(Order order){
        return orderRepository.save(order);
    }


}
