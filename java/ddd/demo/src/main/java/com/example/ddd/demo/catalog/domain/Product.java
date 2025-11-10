package com.example.ddd.demo.catalog.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "product")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {
    @EmbeddedId
    private ProductId id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "stock_quantity", nullable = false)
    private Integer stockQuantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ProductStatus status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "visible")
    private boolean visible = true;

    public Product(String name, String description, BigDecimal price,
                   Integer stockQuantity, Category category) {
        validateProduct(name, price, stockQuantity, category);

        this.id = ProductId.generate();
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.category = category;
        this.status = ProductStatus.PREPARING;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    private void validateProduct(String name, BigDecimal price, Integer stockQuantity, Category category) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("상품명은 필수입니다.");
        }
        if (price == null || price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("상품 가격은 0 이상이어야 합니다.");
        }
        if (stockQuantity == null || stockQuantity < 0) {
            throw new IllegalArgumentException("재고 수량은 0 이상이어야 합니다.");
        }
        if (category == null) {
            throw new IllegalArgumentException("카테고리는 필수입니다.");
        }
    }

    public void changeName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("상품명은 필수입니다.");
        }
        this.name = name;
        this.updatedAt = LocalDateTime.now();
    }

    public void changeDescription(String description) {
        this.description = description;
        this.updatedAt = LocalDateTime.now();
    }

    public void changePrice(BigDecimal price) {
        if (price == null || price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("상품 가격은 0 이상이어야 합니다.");
        }
        this.price = price;
        this.updatedAt = LocalDateTime.now();
    }

    public void changeCategory(Category category) {
        if (category == null) {
            throw new IllegalArgumentException("카테고리는 필수입니다.");
        }
        this.category = category;
        this.updatedAt = LocalDateTime.now();
    }

    public void addStock(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("추가할 재고 수량은 0보다 커야 합니다.");
        }
        this.stockQuantity += quantity;
        this.updatedAt = LocalDateTime.now();
    }

    public void removeStock(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("차감할 재고 수량은 0보다 커야 합니다.");
        }
        if (this.stockQuantity < quantity) {
            throw new IllegalArgumentException("재고가 부족합니다.");
        }
        this.stockQuantity -= quantity;
        this.updatedAt = LocalDateTime.now();

        if (this.stockQuantity == 0 && this.status == ProductStatus.AVAILABLE) {
            this.status = ProductStatus.OUT_OF_STOCK;
        }
    }

    public void makeAvailable() {
        if (this.stockQuantity > 0) {
            this.status = ProductStatus.AVAILABLE;
        } else {
            this.status = ProductStatus.OUT_OF_STOCK;
        }
        this.updatedAt = LocalDateTime.now();
    }

    public void discontinue() {
        this.status = ProductStatus.DISCONTINUED;
        this.updatedAt = LocalDateTime.now();
    }

    public void hide() {
        this.visible = false;
        this.updatedAt = LocalDateTime.now();
    }

    public void show() {
        this.visible = true;
        this.updatedAt = LocalDateTime.now();
    }

    public boolean canOrder(int quantity) {
        return status.canOrder() && visible && stockQuantity >= quantity;
    }

    public boolean isAvailable() {
        return status.isAvailable() && visible && stockQuantity > 0;
    }
}