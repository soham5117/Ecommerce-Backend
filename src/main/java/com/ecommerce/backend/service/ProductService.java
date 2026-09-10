package com.ecommerce.backend.service;

import com.ecommerce.backend.exception.ResourceNotFoundException;
import com.ecommerce.backend.model.Product;
import com.ecommerce.backend.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<String> getAllCategories() {
        return productRepository.findAll().stream()
                .map(Product::getCategory)
                .distinct()
                .sorted()
                .toList();
    }

    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategoryIgnoreCase(category);
    }

    public Product getProductById(String id) {
        return productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Product not found with id: " + id
                        )
                );
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Add a new product
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }
}