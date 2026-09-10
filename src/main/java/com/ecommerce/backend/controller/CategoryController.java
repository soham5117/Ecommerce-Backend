package com.ecommerce.backend.controller;

import com.ecommerce.backend.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final ProductService productService;

    public CategoryController(ProductService productService) {
        this.productService = productService;
    }

    // GET /api/categories
    @GetMapping
    public ResponseEntity<List<String>> getAllCategories() {
        return ResponseEntity.ok(productService.getAllCategories());
    }
}
