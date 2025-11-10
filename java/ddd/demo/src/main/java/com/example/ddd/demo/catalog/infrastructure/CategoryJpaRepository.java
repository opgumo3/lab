package com.example.ddd.demo.catalog.infrastructure;

import com.example.ddd.demo.catalog.domain.Category;
import com.example.ddd.demo.catalog.domain.CategoryId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoryJpaRepository extends JpaRepository<Category, CategoryId> {

    Optional<Category> findByName(String name);

    List<Category> findByParentIsNull();

    List<Category> findByParent(Category parent);

    List<Category> findByVisible(boolean visible);

    @Query("SELECT c FROM Category c WHERE c.parent IS NULL AND c.visible = true ORDER BY c.displayOrder")
    List<Category> findRootCategoriesOrderByDisplayOrder();

    @Query("SELECT c FROM Category c WHERE c.parent = :parent ORDER BY c.displayOrder")
    List<Category> findByParentOrderByDisplayOrder(Category parent);

    boolean existsByName(String name);
}