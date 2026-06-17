package com.northwind.northwind_api.controller;

import com.northwind.northwind_api.model.Category;

import com.northwind.northwind_api.model.Product;
import com.northwind.northwind_api.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "*")
public class CategoryController {
    private final CategoryService categoryService;
    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }
    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories(){
        return ResponseEntity.ok(categoryService.getAllCategories());
    }
    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> getCategoryInCategoryId(@PathVariable Long categoryId){
        return ResponseEntity.ok(categoryService.getCategoryById(categoryId));
    }
    @GetMapping("/search/{categoryName}")
    public ResponseEntity<List<Category>>getCategoryWithCategoryName(@PathVariable String categoryName){
        return ResponseEntity.ok(categoryService.getCategoryByName(categoryName));
    }
    @PostMapping
    public ResponseEntity<Category>addNewCategory(@RequestBody Category category){
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.createCategory(category));
    }
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long categoryId){
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{categoryId}")
    public ResponseEntity<Category>updateCategory(
            @PathVariable Long categoryId,
            @RequestBody Category category){
        return ResponseEntity.ok(categoryService.updateCategory(categoryId,category));
    }

}
