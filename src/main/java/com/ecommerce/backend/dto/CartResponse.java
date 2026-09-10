package com.ecommerce.backend.dto;

import com.ecommerce.backend.model.CartItem;

import java.util.List;

public record CartResponse(
        List<CartItem> items,
        int totalItems,
        double totalAmount
) {
}
