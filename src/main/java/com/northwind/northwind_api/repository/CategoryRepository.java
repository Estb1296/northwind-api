package com.northwind.northwind_api.repository;

import com.northwind.northwind_api.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    List<Category> findByCategoryNameContainingIgnoreCase(String categoryName);
}
