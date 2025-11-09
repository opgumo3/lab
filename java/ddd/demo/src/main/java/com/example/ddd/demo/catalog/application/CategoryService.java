package com.example.ddd.demo.catalog.application;

import com.example.ddd.demo.catalog.domain.Category;
import com.example.ddd.demo.catalog.domain.CategoryId;
import com.example.ddd.demo.catalog.domain.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryId createCategory(String name, String description, CategoryId parentId) {
        Category parent = null;
        if (parentId != null) {
            parent = findCategoryById(parentId);
        }

        if (categoryRepository.existsByName(name)) {
            throw new IllegalArgumentException("이미 존재하는 카테고리명입니다: " + name);
        }

        Category category = new Category(name, description, parent);
        categoryRepository.save(category);
        return category.getId();
    }

    public void updateCategory(CategoryId categoryId, String name, String description) {
        Category category = findCategoryById(categoryId);

        if (!category.getName().equals(name) && categoryRepository.existsByName(name)) {
            throw new IllegalArgumentException("이미 존재하는 카테고리명입니다: " + name);
        }

        category.changeName(name);
        category.changeDescription(description);
    }

    public void changeCategoryParent(CategoryId categoryId, CategoryId newParentId) {
        Category category = findCategoryById(categoryId);
        Category newParent = null;

        if (newParentId != null) {
            newParent = findCategoryById(newParentId);
        }

        category.changeParent(newParent);
    }

    public void changeCategoryDisplayOrder(CategoryId categoryId, Integer displayOrder) {
        Category category = findCategoryById(categoryId);
        category.changeDisplayOrder(displayOrder);
    }

    public void hideCategory(CategoryId categoryId) {
        Category category = findCategoryById(categoryId);
        category.hide();
    }

    public void showCategory(CategoryId categoryId) {
        Category category = findCategoryById(categoryId);
        category.show();
    }

    public void deleteCategory(CategoryId categoryId) {
        Category category = findCategoryById(categoryId);

        if (!category.getChildren().isEmpty()) {
            throw new IllegalStateException("하위 카테고리가 있는 카테고리는 삭제할 수 없습니다.");
        }

        categoryRepository.delete(category);
    }

    @Transactional(readOnly = true)
    public Category findCategoryById(CategoryId categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("카테고리를 찾을 수 없습니다: " + categoryId));
    }

    @Transactional(readOnly = true)
    public List<Category> findRootCategories() {
        return categoryRepository.findRootCategories();
    }

    @Transactional(readOnly = true)
    public List<Category> findSubCategories(CategoryId parentId) {
        Category parent = findCategoryById(parentId);
        return categoryRepository.findByParent(parent);
    }

    @Transactional(readOnly = true)
    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Category> findVisibleCategories() {
        return categoryRepository.findByVisible(true);
    }
}