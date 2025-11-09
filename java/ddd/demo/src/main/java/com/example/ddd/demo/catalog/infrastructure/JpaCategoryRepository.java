package com.example.ddd.demo.catalog.infrastructure;

import com.example.ddd.demo.catalog.domain.Category;
import com.example.ddd.demo.catalog.domain.CategoryId;
import com.example.ddd.demo.catalog.domain.CategoryRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaCategoryRepository implements CategoryRepository {

    private final CategoryJpaRepository categoryJpaRepository;

    public JpaCategoryRepository(CategoryJpaRepository categoryJpaRepository) {
        this.categoryJpaRepository = categoryJpaRepository;
    }

    @Override
    public Optional<Category> findById(CategoryId id) {
        return categoryJpaRepository.findById(id);
    }

    @Override
    public Optional<Category> findByName(String name) {
        return categoryJpaRepository.findByName(name);
    }

    @Override
    public List<Category> findRootCategories() {
        return categoryJpaRepository.findRootCategoriesOrderByDisplayOrder();
    }

    @Override
    public List<Category> findByParent(Category parent) {
        return categoryJpaRepository.findByParentOrderByDisplayOrder(parent);
    }

    @Override
    public List<Category> findByParentIsNull() {
        return categoryJpaRepository.findByParentIsNull();
    }

    @Override
    public List<Category> findByVisible(boolean visible) {
        return categoryJpaRepository.findByVisible(visible);
    }

    @Override
    public List<Category> findAll() {
        return categoryJpaRepository.findAll();
    }

    @Override
    public void save(Category category) {
        categoryJpaRepository.save(category);
    }

    @Override
    public void delete(Category category) {
        categoryJpaRepository.delete(category);
    }

    @Override
    public boolean existsByName(String name) {
        return categoryJpaRepository.existsByName(name);
    }
}