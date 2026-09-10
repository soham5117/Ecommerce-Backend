package com.ecommerce.backend.service;

import com.ecommerce.backend.dto.LoginRequest;
import com.ecommerce.backend.dto.LoginResponse;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Login is simulated per the task spec (no real user store / real auth
 * provider is required). Any request that passes validation succeeds
 * and receives a dummy session token.
 */
@Service
public class AuthService {

    public LoginResponse login(LoginRequest request) {
        String dummyToken = UUID.randomUUID().toString();
        return new LoginResponse(true, "Login successful", dummyToken, request.email());
    }
}
