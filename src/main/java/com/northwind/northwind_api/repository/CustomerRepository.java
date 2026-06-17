package com.northwind.northwind_api.repository;

import com.northwind.northwind_api.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,String> {
List<Customer>findByCompanyName(String companyName);
}
