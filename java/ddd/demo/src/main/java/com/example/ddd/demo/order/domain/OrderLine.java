package com.example.ddd.demo.order.domain;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Objects;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderLine {
    private String productId;
    private String productName;
    private BigDecimal price;
    private int quantity;

    public OrderLine(String productId, String productName, BigDecimal price, int quantity) {
        if (productId == null || productId.trim().isEmpty()) {
            throw new IllegalArgumentException("상품 ID는 필수입니다.");
        }
        if (productName == null || productName.trim().isEmpty()) {
            throw new IllegalArgumentException("상품명은 필수입니다.");
        }
        if (price == null || price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("상품 가격은 0 이상이어야 합니다.");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("주문 수량은 0보다 커야 합니다.");
        }

        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }

    public BigDecimal getAmounts() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderLine orderLine = (OrderLine) o;
        return quantity == orderLine.quantity &&
               Objects.equals(productId, orderLine.productId) &&
               Objects.equals(productName, orderLine.productName) &&
               Objects.equals(price, orderLine.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, productName, price, quantity);
    }
}