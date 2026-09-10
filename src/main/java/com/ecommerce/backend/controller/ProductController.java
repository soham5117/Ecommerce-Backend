package com.ecommerce.backend.controller;

import com.ecommerce.backend.model.Product;
import com.ecommerce.backend.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@Tag(name = "Products", description = "Product management APIs")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // GET /api/products
    @GetMapping
    @Operation(summary = "Get all products")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    // GET /api/products/category/{category}
    @GetMapping("/category/{category}")
    @Operation(summary = "Get products by category")
    public ResponseEntity<List<Product>> getProductsByCategory(
            @PathVariable String category) {

        return ResponseEntity.ok(
                productService.getProductsByCategory(category)
        );
    }

    // GET /api/products/{id}
    @GetMapping("/{id}")
    @Operation(summary = "Get product by ID")
    public ResponseEntity<Product> getProductById(
            @PathVariable String id) {

        return ResponseEntity.ok(
                productService.getProductById(id)
        );
    }

    // POST /api/products
    @PostMapping
    @Operation(summary = "Add a new product")
    public ResponseEntity<Product> addProduct(
            @RequestBody Product product) {

        Product savedProduct = productService.addProduct(product);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedProduct);
    }
}