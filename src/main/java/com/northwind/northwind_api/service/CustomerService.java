package com.northwind.northwind_api.service;

import com.northwind.northwind_api.model.Customer;
import com.northwind.northwind_api.model.Order;
import com.northwind.northwind_api.repository.CustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {
private CustomerRepository customerRepository;
public CustomerService(CustomerRepository customerRepository){
    this.customerRepository = customerRepository;
}
public List<Customer>getAllCustomers(){
    return customerRepository.findAll();
}
public Customer getCustomerById(String customerId){
    return customerRepository.findById(customerId)
            .map(customer -> {
                // 2. Process or log your single customer here
                System.out.println("Processing customer: " + customer.getCustomerId());
                return customer;
            })
            .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Customer not found with id: " + customerId));
}
    public List<Customer>getCustomerByCompanyName(String companyName){
        List<Customer>customers = customerRepository.findByCompanyName(companyName);
        if(customers.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No customers found with company name: " + companyName);
        }
        return customers;
    }
    public Customer updateCustomer(String customerId, Customer updatedCustomer) {
        return customerRepository.findById(customerId)
                .map(existing -> {
                    existing.setCompanyName(updatedCustomer.getCompanyName());
                    existing.setContactName(updatedCustomer.getContactName());
                    existing.setContactTitle(updatedCustomer.getContactTitle());
                    existing.setCity(updatedCustomer.getCity());

                    // 3. Save and return the single entity
                    return customerRepository.save(existing);
                })
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Cannot find customer with id: " + customerId));
    }
    public void deleteCustomer(String customerId) {
        customerRepository.findById(customerId)
                .ifPresentOrElse(
                        customer -> customerRepository.deleteById(customerId),
                        ()-> {
                            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Can not find order to be deleted");
                        })
        ;
    }
}
