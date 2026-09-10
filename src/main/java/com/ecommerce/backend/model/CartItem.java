package com.ecommerce.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "cart_items")
public class CartItem {

    @Id
    private String id;

    private String productId;

    private String title;

    private double price;

    private String image;

    private int quantity;
}
