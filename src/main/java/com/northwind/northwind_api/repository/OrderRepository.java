package com.northwind.northwind_api.repository;

import com.northwind.northwind_api.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
List<Order>findByCustomerCustomerId(String customerId);
List<Order> findByShipNameContainingIgnoreCase(String shipName);
@Query("SELECT o FROM Order o LEFT JOIN FETCH o.customer")
List<Order>findAllWithCustomer();
}
