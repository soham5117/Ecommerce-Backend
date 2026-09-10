package com.ecommerce.backend.config;

import com.ecommerce.backend.model.Product;
import com.ecommerce.backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Seeds the "products" collection with sample data on startup so the API
 * has something to serve without needing a separate import step.
 * Set app.seed-data=false in application.yml to disable.
 */
@Component
public class DataSeeder implements CommandLineRunner {

    private final ProductRepository productRepository;

    @Value("${app.seed-data:true}")
    private boolean seedData;

    public DataSeeder(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) {
        if (!seedData || productRepository.count() > 0) {
            return;
        }

        List<Product> products = List.of(
                new Product(null, "Men's Cotton Crew T-Shirt", 599.00,
                        "Breathable cotton t-shirt, perfect for everyday wear.",
                        "men's clothing",
                        "https://fakestoreapi.com/img/71-3HjGNDUL._AC_SY879._SX._UX._SY._UY_.jpg"),
                new Product(null, "Slim Fit Casual Shirt", 1499.00,
                        "Slim-fit shirt with a soft-touch finish, great for casual outings.",
                        "men's clothing",
                        "https://fakestoreapi.com/img/71YXzeOuslL._AC_UY879_.jpg"),
                new Product(null, "Men's Casual Premium Jacket", 3499.00,
                        "Windproof and lightweight jacket with multiple pockets.",
                        "men's clothing",
                        "https://fakestoreapi.com/img/81XH0e8fefL._AC_UY879_.jpg"),

                new Product(null, "Women's Floral Summer Dress", 1799.00,
                        "Lightweight floral dress ideal for warm weather.",
                        "women's clothing",
                        "https://fakestoreapi.com/img/71z3kpMAYsL._AC_UY879_.jpg"),
                new Product(null, "Women's Boyfriend Denim Jacket", 2599.00,
                        "Classic denim jacket with a relaxed boyfriend fit.",
                        "women's clothing",
                        "https://fakestoreapi.com/img/81XH0e8fefL._AC_UY879_.jpg"),
                new Product(null, "Women's Rain Jacket", 2199.00,
                        "Waterproof rain jacket with hood, packable design.",
                        "women's clothing",
                        "https://fakestoreapi.com/img/71HblAHs5xL._AC_UY879_-2.jpg"),

                new Product(null, "Wireless Bluetooth Headphones", 2999.00,
                        "Over-ear headphones with noise isolation and 20-hour battery life.",
                        "electronics",
                        "https://fakestoreapi.com/img/61pHAEJ4NML._AC_UX679_.jpg"),
                new Product(null, "27-inch 4K Monitor", 24999.00,
                        "Ultra HD monitor with vivid colors and thin bezels.",
                        "electronics",
                        "https://fakestoreapi.com/img/81QpkIctqPL._AC_SX679_.jpg"),
                new Product(null, "Portable External SSD 1TB", 6999.00,
                        "Compact high-speed SSD for fast file transfers on the go.",
                        "electronics",
                        "https://fakestoreapi.com/img/61IBBVJvSDL._AC_SY879_.jpg"),
                new Product(null, "Smart Fitness Watch", 4499.00,
                        "Tracks heart rate, sleep, and workouts with a week-long battery.",
                        "electronics",
                        "https://fakestoreapi.com/img/71kWymZ+c+L._AC_SX679_.jpg"),

                new Product(null, "Gold Plated Hoop Earrings", 899.00,
                        "Elegant gold-plated hoop earrings for everyday glam.",
                        "jewelery",
                        "https://fakestoreapi.com/img/51UDEzMJVpL._AC_UL640_QL65_ML3_.jpg"),
                new Product(null, "Sterling Silver Pendant Necklace", 1299.00,
                        "Minimalist sterling silver necklace with adjustable chain.",
                        "jewelery",
                        "https://fakestoreapi.com/img/71pWzhdJNwL._AC_UL640_QL65_ML3_.jpg"),
                new Product(null, "Men's Stainless Steel Bracelet", 1099.00,
                        "Durable stainless steel bracelet with a modern clasp.",
                        "jewelery",
                        "https://fakestoreapi.com/img/71YAIFU48IL._AC_UL640_QL65_ML3_.jpg")
        );

        productRepository.saveAll(products);
    }
}
