# E-Commerce Mini App — Backend

Spring Boot (Java 21) + MongoDB backend for the Flutter e-commerce mini app
(Splash → Login → Dashboard/Categories → Product List → Product Details → Cart).

## Tech

- Java 21
- Spring Boot 3.3.4 (Web, Validation, Spring Data MongoDB)
- Gradle
- MongoDB

## Folder structure

```
ecommerce-backend/
├── build.gradle
├── settings.gradle
├── src/main/java/com/ecommerce/backend/
│   ├── EcommerceBackendApplication.java
│   ├── config/
│   │   ├── CorsConfig.java        # allows the Flutter app to call the API
│   │   └── DataSeeder.java        # seeds sample products into Mongo on first run
│   ├── controller/
│   │   ├── AuthController.java
│   │   ├── CategoryController.java
│   │   ├── ProductController.java
│   │   └── CartController.java
│   ├── dto/                       # request/response records
│   ├── exception/                 # global error handling
│   ├── model/                     # Product, CartItem (Mongo documents)
│   ├── repository/                # Spring Data Mongo repositories
│   └── service/                   # business logic
└── src/main/resources/
    └── application.yml
```

## Prerequisites

- JDK 21
- MongoDB running locally on `mongodb://localhost:27017` (or update the URI in
  `application.yml`)
- Gradle 8.x (or generate the wrapper once with `gradle wrapper` if you'd
  rather use `./gradlew`)

## Run

```bash
# start Mongo (example using Docker)
docker run -d -p 27017:27017 --name ecommerce-mongo mongo:7

# from the project root
gradle bootRun
```

The API starts on `http://localhost:8080`. On first startup, `DataSeeder`
inserts ~13 sample products across 4 categories (`electronics`,
`jewelery`, `men's clothing`, `women's clothing`) so the endpoints return
data immediately — no manual import needed. Set `app.seed-data: false` in
`application.yml` to skip this after the first run.

For a device/emulator to reach a backend running on your machine, point the
Flutter app at `http://10.0.2.2:8080` (Android emulator) or your machine's
LAN IP (physical device) instead of `localhost`.

## API Reference

### Auth (simulated — no real user store)
| Method | Endpoint          | Body                              | Notes                               |
|--------|-------------------|------------------------------------|--------------------------------------|
| POST   | `/api/auth/login` | `{ "email": "...", "password": "..." }` | Validates email format + password ≥ 6 chars, returns a dummy token |

### Categories
| Method | Endpoint          | Notes                                |
|--------|-------------------|----------------------------------------|
| GET    | `/api/categories` | Distinct category names from products |

### Products
| Method | Endpoint                            | Notes                          |
|--------|--------------------------------------|---------------------------------|
| GET    | `/api/products`                     | All products                    |
| GET    | `/api/products/category/{category}` | Products in a category          |
| GET    | `/api/products/{id}`                | Single product (for details screen) |

### Cart
| Method | Endpoint              | Body                                  | Notes                                   |
|--------|------------------------|-----------------------------------------|-------------------------------------------|
| GET    | `/api/cart`            | –                                        | Items + totalItems + totalAmount          |
| POST   | `/api/cart`             | `{ "productId": "...", "quantity": 1 }` | Adds product, or increments if already in cart |
| PUT    | `/api/cart/{cartItemId}`| `{ "quantity": 3 }`                     | Sets exact quantity (used by +/- controls) |
| DELETE | `/api/cart/{cartItemId}`| –                                        | Removes one line item                      |
| DELETE | `/api/cart`             | –                                        | Clears the whole cart                      |

All responses are JSON; validation and not-found errors return a consistent
`{ "success": false, "message": "..." }` shape (see `GlobalExceptionHandler`).

## Example requests

```bash
# login
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email":"user@test.com","password":"secret1"}'

# categories
curl http://localhost:8080/api/categories

# products in a category
curl http://localhost:8080/api/products/category/electronics

# add to cart
curl -X POST http://localhost:8080/api/cart \
  -H "Content-Type: application/json" \
  -d '{"productId":"<product-id-from-above>","quantity":1}'

# view cart
curl http://localhost:8080/api/cart
```
