package com.example.ddd.demo.catalog.domain;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.UUID;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CategoryId {
    private String value;

    public CategoryId(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("카테고리 ID는 필수입니다.");
        }
        this.value = value;
    }

    public static CategoryId generate() {
        return new CategoryId(UUID.randomUUID().toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryId that = (CategoryId) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }
}