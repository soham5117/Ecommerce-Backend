package com.ecommerce.backend.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record AddToCartRequest(

        @NotBlank(message = "productId is required")
        String productId,

        @Min(value = 1, message = "quantity must be at least 1")
        Integer quantity
) {
}
