package com.example.ddd.demo.catalog.domain;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Optional<Product> findById(ProductId id);

    Optional<Product> findByName(String name);

    List<Product> findByCategory(Category category);

    List<Product> findByCategoryId(CategoryId categoryId);

    List<Product> findByStatus(ProductStatus status);

    List<Product> findByVisible(boolean visible);

    List<Product> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);

    List<Product> findAvailableProducts();

    List<Product> findByNameContaining(String keyword);

    List<Product> findAll();

    void save(Product product);

    void delete(Product product);

    boolean existsByName(String name);

    long countByCategory(Category category);

    long countByStatus(ProductStatus status);
}