package com.northwind.northwind_api.service;

import com.northwind.northwind_api.model.Product;
import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Service;
import com.northwind.northwind_api.repository.ProductRepository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService (ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public List<Product>getAllProducts(){
        return productRepository.findAllWithCategory();
    }

    public Product getProductByProductId(Long productId){
        return productRepository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Product not found with id: " + productId));
    }

    public List<Product> getProductByCategoryId(Long categoryId) {
        List<Product> products = productRepository.findByCategory_CategoryId(categoryId);
        if (products.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No products found for category: " + categoryId);
        }
        return products;
    }
    public List<Product> getProductByProductName(String productName) {
        List<Product>products = productRepository.findByProductNameContainingIgnoreCase(productName);
        if(products.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No product found by this name: " + productName);
        }
        return products;
    }
    public void deleteProduct(Long productId) {
        productRepository.findById(productId)
                .ifPresentOrElse(
                        product -> productRepository.deleteById(productId),
                        ()-> {
                            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Can not find product to be deleted");
                        })
                    ;
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
