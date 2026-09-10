package com.ecommerce.backend.controller;

import com.ecommerce.backend.dto.AddToCartRequest;
import com.ecommerce.backend.dto.CartResponse;
import com.ecommerce.backend.dto.UpdateQuantityRequest;
import com.ecommerce.backend.service.CartService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    // GET /api/cart
    @GetMapping
    public ResponseEntity<CartResponse> getCart() {
        return ResponseEntity.ok(cartService.getCart());
    }

    // POST /api/cart  -> add a product (or increment if already present)
    @PostMapping
    public ResponseEntity<CartResponse> addToCart(@Valid @RequestBody AddToCartRequest request) {
        int quantity = request.quantity() == null ? 1 : request.quantity();
        return ResponseEntity.ok(cartService.addToCart(request.productId(), quantity));
    }

    // PUT /api/cart/{cartItemId}  -> set an exact quantity (+/- controls)
    @PutMapping("/{cartItemId}")
    public ResponseEntity<CartResponse> updateQuantity(@PathVariable String cartItemId,
                                                         @Valid @RequestBody UpdateQuantityRequest request) {
        return ResponseEntity.ok(cartService.updateQuantity(cartItemId, request.quantity()));
    }

    // DELETE /api/cart/{cartItemId}  -> remove one line item
    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<CartResponse> removeFromCart(@PathVariable String cartItemId) {
        return ResponseEntity.ok(cartService.removeFromCart(cartItemId));
    }

    // DELETE /api/cart  -> clear the whole cart
    @DeleteMapping
    public ResponseEntity<CartResponse> clearCart() {
        return ResponseEntity.ok(cartService.clearCart());
    }
}
