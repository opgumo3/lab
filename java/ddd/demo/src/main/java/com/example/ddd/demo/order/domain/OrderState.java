package com.example.ddd.demo.order.domain;

public enum OrderState {
    PAYMENT_WAITING("결제 대기"),
    PREPARING("상품 준비중"),
    SHIPPED("배송중"),
    DELIVERING("배송중"),
    DELIVERY_COMPLETED("배송 완료"),
    CANCELED("취소됨");

    private final String description;

    OrderState(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public boolean isShippingChangeable() {
        return this == PAYMENT_WAITING || this == PREPARING;
    }

    public boolean isCancelable() {
        return this == PAYMENT_WAITING || this == PREPARING;
    }
}