package com.ecommerce.backend.service;

import com.ecommerce.backend.dto.CartResponse;
import com.ecommerce.backend.exception.ResourceNotFoundException;
import com.ecommerce.backend.model.CartItem;
import com.ecommerce.backend.model.Product;
import com.ecommerce.backend.repository.CartItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    private final CartItemRepository cartItemRepository;
    private final ProductService productService;

    public CartService(CartItemRepository cartItemRepository, ProductService productService) {
        this.cartItemRepository = cartItemRepository;
        this.productService = productService;
    }

    public CartResponse getCart() {
        return buildCartResponse(cartItemRepository.findAll());
    }

    public CartResponse addToCart(String productId, int quantity) {
        Product product = productService.getProductById(productId);

        CartItem item = cartItemRepository.findByProductId(productId)
                .map(existing -> {
                    existing.setQuantity(existing.getQuantity() + quantity);
                    return existing;
                })
                .orElseGet(() -> new CartItem(null, product.getId(), product.getTitle(),
                        product.getPrice(), product.getImage(), quantity));

        cartItemRepository.save(item);
        return getCart();
    }

    public CartResponse updateQuantity(String cartItemId, int quantity) {
        CartItem item = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart item not found with id: " + cartItemId));
        item.setQuantity(quantity);
        cartItemRepository.save(item);
        return getCart();
    }

    public CartResponse removeFromCart(String cartItemId) {
        if (!cartItemRepository.existsById(cartItemId)) {
            throw new ResourceNotFoundException("Cart item not found with id: " + cartItemId);
        }
        cartItemRepository.deleteById(cartItemId);
        return getCart();
    }

    public CartResponse clearCart() {
        cartItemRepository.deleteAll();
        return getCart();
    }

    private CartResponse buildCartResponse(List<CartItem> items) {
        int totalItems = items.stream().mapToInt(CartItem::getQuantity).sum();
        double totalAmount = items.stream()
                .mapToDouble(i -> i.getPrice() * i.getQuantity())
                .sum();
        return new CartResponse(items, totalItems, Math.round(totalAmount * 100.0) / 100.0);
    }
}
