package com.northwind.northwind_api.service;

import com.northwind.northwind_api.model.Product;
import org.springframework.stereotype.Service;
import com.northwind.northwind_api.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService (ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public static class ProductNotFoundException extends RuntimeException {
        public ProductNotFoundException(String message) {
            super(message);
        }
    }

    public List<Product>getAllProducts(){
        return productRepository.findAll();
    }

    public Product getProductByProductId(Long productId){
        return productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + productId));
    }

    public List<Product> getProductByCategoryId(Long categoryId) {
        List<Product> products = productRepository.findByCategoryId(categoryId);
        if (products.isEmpty()) {
            throw new ProductNotFoundException("No products found for category: " + categoryId);
        }
        return products;
    }
    public List<Product> getProductByProductName(String productName) {
        List<Product>products = productRepository.findByProductNameContainingIgnoreCase(productName);
        if(products.isEmpty()){
            throw new ProductNotFoundException("No product found by this name: " + productName);
        }
        return products;
    }
    public void deleteProduct(Long productId) {
        if(productRepository.existsById(productId))
        {
        productRepository.deleteById(productId);
        }
    }

    public Product updateProduct(Long productId, Product updatedProduct) {
        Product existing = getProductByProductId(productId);
        existing.setProductName(updatedProduct.getProductName());
        existing.setDiscontinued(updatedProduct.getDiscontinued());
        return productRepository.save(existing);
    }
    public Product createProduct(Product product){
        return productRepository.save(product);
    }
}
