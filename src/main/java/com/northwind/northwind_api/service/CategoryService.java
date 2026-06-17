package com.northwind.northwind_api.service;

import com.northwind.northwind_api.model.Category;

import com.northwind.northwind_api.repository.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    public List<Category>getAllCategories(){
        return categoryRepository.findAll();
    }
    public Category getCategoryById(Long categoryId){
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found with id: " + categoryId));
    }
    public List<Category>getCategoryByName(String categoryName){
        List<Category>categories = categoryRepository.findByCategoryNameContainingIgnoreCase(categoryName);
        if(categories.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found with this name: "+categoryName);
        }
        return categories;
    }

    public void deleteCategory(Long categoryId){
        categoryRepository.findById(categoryId)
                .ifPresentOrElse(
                        category -> categoryRepository.deleteById(categoryId),
                        () -> {
                            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                                    "Cannot delete: category not found with id: " + categoryId);
                        }
                );
    }
    public Category createCategory(Category category){
        return categoryRepository.save(category);
    }
}











