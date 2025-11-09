package com.example.ddd.demo.catalog.infrastructure;

import com.example.ddd.demo.catalog.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductJpaRepository extends JpaRepository<Product, ProductId> {

    Optional<Product> findByName(String name);

    List<Product> findByCategory(Category category);

    @Query("SELECT p FROM Product p WHERE p.category.id = :categoryId")
    List<Product> findByCategoryId(CategoryId categoryId);

    List<Product> findByStatus(ProductStatus status);

    List<Product> findByVisible(boolean visible);

    List<Product> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);

    @Query("SELECT p FROM Product p WHERE p.status = 'AVAILABLE' AND p.visible = true AND p.stockQuantity > 0")
    List<Product> findAvailableProducts();

    List<Product> findByNameContainingIgnoreCase(String keyword);

    boolean existsByName(String name);

    long countByCategory(Category category);

    long countByStatus(ProductStatus status);

    @Query("SELECT p FROM Product p WHERE p.category.id = :categoryId AND p.status = 'AVAILABLE' AND p.visible = true")
    List<Product> findAvailableProductsByCategoryId(CategoryId categoryId);
}