package com.example.ddd.demo.catalog.ui;

import com.example.ddd.demo.catalog.application.ProductService;
import com.example.ddd.demo.catalog.domain.CategoryId;
import com.example.ddd.demo.catalog.domain.Product;
import com.example.ddd.demo.catalog.domain.ProductId;
import com.example.ddd.demo.catalog.domain.ProductStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody CreateProductRequest request) {
        ProductId productId = productService.createProduct(
                request.getName(),
                request.getDescription(),
                request.getPrice(),
                request.getStockQuantity(),
                new CategoryId(request.getCategoryId())
        );
        return ResponseEntity.ok(productId.getValue());
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Void> updateProduct(
            @PathVariable String productId,
            @RequestBody UpdateProductRequest request) {
        productService.updateProduct(
                new ProductId(productId),
                request.getName(),
                request.getDescription(),
                request.getPrice(),
                new CategoryId(request.getCategoryId())
        );
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{productId}/stock/add")
    public ResponseEntity<Void> addStock(
            @PathVariable String productId,
            @RequestBody AddStockRequest request) {
        productService.addStock(new ProductId(productId), request.getQuantity());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{productId}/stock/remove")
    public ResponseEntity<Void> removeStock(
            @PathVariable String productId,
            @RequestBody RemoveStockRequest request) {
        productService.removeStock(new ProductId(productId), request.getQuantity());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{productId}/make-available")
    public ResponseEntity<Void> makeProductAvailable(@PathVariable String productId) {
        productService.makeProductAvailable(new ProductId(productId));
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{productId}/discontinue")
    public ResponseEntity<Void> discontinueProduct(@PathVariable String productId) {
        productService.discontinueProduct(new ProductId(productId));
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{productId}/hide")
    public ResponseEntity<Void> hideProduct(@PathVariable String productId) {
        productService.hideProduct(new ProductId(productId));
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{productId}/show")
    public ResponseEntity<Void> showProduct(@PathVariable String productId) {
        productService.showProduct(new ProductId(productId));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String productId) {
        productService.deleteProduct(new ProductId(productId));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable String productId) {
        Product product = productService.findProductById(new ProductId(productId));
        return ResponseEntity.ok(product);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable String categoryId) {
        List<Product> products = productService.findProductsByCategory(new CategoryId(categoryId));
        return ResponseEntity.ok(products);
    }

    @GetMapping("/available")
    public ResponseEntity<List<Product>> getAvailableProducts() {
        List<Product> products = productService.findAvailableProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Product>> getProductsByStatus(@PathVariable ProductStatus status) {
        List<Product> products = productService.findProductsByStatus(status);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/price-range")
    public ResponseEntity<List<Product>> getProductsByPriceRange(
            @RequestParam BigDecimal minPrice,
            @RequestParam BigDecimal maxPrice) {
        List<Product> products = productService.findProductsByPriceRange(minPrice, maxPrice);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword) {
        List<Product> products = productService.searchProducts(keyword);
        return ResponseEntity.ok(products);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.findAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{productId}/can-order")
    public ResponseEntity<Boolean> canOrderProduct(
            @PathVariable String productId,
            @RequestParam int quantity) {
        boolean canOrder = productService.canOrderProduct(new ProductId(productId), quantity);
        return ResponseEntity.ok(canOrder);
    }
}