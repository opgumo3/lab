package com.example.ddd.demo.catalog.domain;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {
    Optional<Category> findById(CategoryId id);

    Optional<Category> findByName(String name);

    List<Category> findRootCategories();

    List<Category> findByParent(Category parent);

    List<Category> findByParentIsNull();

    List<Category> findByVisible(boolean visible);

    List<Category> findAll();

    void save(Category category);

    void delete(Category category);

    boolean existsByName(String name);
}