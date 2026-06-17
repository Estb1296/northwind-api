package com.northwind.northwind_api.controller;


import com.northwind.northwind_api.model.Customer;
import com.northwind.northwind_api.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }
    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable String customerId) {
        // Your service handles the 404 exception internally via .orElseThrow()
        Customer customer = customerService.getCustomerById(customerId);
        return ResponseEntity.ok(customer);
    }
    @GetMapping("/company")
    public ResponseEntity<List<Customer>> getCustomerByCompanyName(@RequestParam("name") String companyName) {
        List<Customer> customers = customerService.getCustomerByCompanyName(companyName);
        return ResponseEntity.ok(customers);
    }
    @PutMapping("/{customerId}")
    public ResponseEntity<Customer> updateCustomer(
            @PathVariable String customerId,
            @RequestBody Customer updatedCustomer) {
        Customer customer = customerService.updateCustomer(customerId, updatedCustomer);
        return ResponseEntity.ok(customer);
    }
    @DeleteMapping("/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelOrder(@PathVariable String customerId) {
        customerService.deleteCustomer(customerId);
    }
}
