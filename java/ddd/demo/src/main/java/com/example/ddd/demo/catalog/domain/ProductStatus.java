package com.example.ddd.demo.catalog.domain;

public enum ProductStatus {
    PREPARING("준비중"),
    AVAILABLE("판매중"),
    OUT_OF_STOCK("품절"),
    DISCONTINUED("단종");

    private final String description;

    ProductStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public boolean isAvailable() {
        return this == AVAILABLE;
    }

    public boolean canOrder() {
        return this == AVAILABLE;
    }
}