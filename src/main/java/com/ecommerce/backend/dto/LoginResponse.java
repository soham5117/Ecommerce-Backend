package com.ecommerce.backend.dto;

public record LoginResponse(
        boolean success,
        String message,
        String token,
        String email
) {
}
