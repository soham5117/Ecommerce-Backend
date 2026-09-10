package com.ecommerce.backend.repository;

import com.ecommerce.backend.model.CartItem;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CartItemRepository extends MongoRepository<CartItem, String> {

    Optional<CartItem> findByProductId(String productId);
}
