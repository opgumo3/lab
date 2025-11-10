package com.example.ddd.demo.catalog.infrastructure;

import com.example.ddd.demo.catalog.domain.*;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaProductRepository implements ProductRepository {

    private final ProductJpaRepository productJpaRepository;

    public JpaProductRepository(ProductJpaRepository productJpaRepository) {
        this.productJpaRepository = productJpaRepository;
    }

    @Override
    public Optional<Product> findById(ProductId id) {
        return productJpaRepository.findById(id);
    }

    @Override
    public Optional<Product> findByName(String name) {
        return productJpaRepository.findByName(name);
    }

    @Override
    public List<Product> findByCategory(Category category) {
        return productJpaRepository.findByCategory(category);
    }

    @Override
    public List<Product> findByCategoryId(CategoryId categoryId) {
        return productJpaRepository.findByCategoryId(categoryId);
    }

    @Override
    public List<Product> findByStatus(ProductStatus status) {
        return productJpaRepository.findByStatus(status);
    }

    @Override
    public List<Product> findByVisible(boolean visible) {
        return productJpaRepository.findByVisible(visible);
    }

    @Override
    public List<Product> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice) {
        return productJpaRepository.findByPriceBetween(minPrice, maxPrice);
    }

    @Override
    public List<Product> findAvailableProducts() {
        return productJpaRepository.findAvailableProducts();
    }

    @Override
    public List<Product> findByNameContaining(String keyword) {
        return productJpaRepository.findByNameContainingIgnoreCase(keyword);
    }

    @Override
    public List<Product> findAll() {
        return productJpaRepository.findAll();
    }

    @Override
    public void save(Product product) {
        productJpaRepository.save(product);
    }

    @Override
    public void delete(Product product) {
        productJpaRepository.delete(product);
    }

    @Override
    public boolean existsByName(String name) {
        return productJpaRepository.existsByName(name);
    }

    @Override
    public long countByCategory(Category category) {
        return productJpaRepository.countByCategory(category);
    }

    @Override
    public long countByStatus(ProductStatus status) {
        return productJpaRepository.countByStatus(status);
    }
}