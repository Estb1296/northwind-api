package com.northwind.northwind_api.controller;

import com.northwind.northwind_api.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.northwind.northwind_api.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {
    private final ProductService productService;
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product>products;
        products=productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Product>> getProductsInCategory(@PathVariable Long categoryId){
        List<Product>products;
        products=productService.getProductByCategoryId(categoryId);
        return ResponseEntity.ok(products);
    }
    @GetMapping("/search/{name}")
    public ResponseEntity<List<Product>>searchProductByName(@PathVariable String productName){
        List<Product>products;
        products=productService.getProductByProductName(productName);
        return ResponseEntity.ok(products);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Product>getProductByProductId(@PathVariable Long productId){
        Product product = productService.getProductByProductId(productId);
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<Product>addNewProduct(@RequestBody Product product){
        Product added = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(added);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Product>updateProduct(
            @PathVariable Long productId,
            @RequestBody Product product){
        Product updated = productService.updateProduct(productId, product);
        return ResponseEntity.ok(updated);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void>deleteProduct(@PathVariable Long productId){
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }
}
