package com.example.ddd.demo.catalog.ui;

import com.example.ddd.demo.catalog.application.CategoryService;
import com.example.ddd.demo.catalog.domain.Category;
import com.example.ddd.demo.catalog.domain.CategoryId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<String> createCategory(@RequestBody CreateCategoryRequest request) {
        CategoryId parentId = request.getParentId() != null ? new CategoryId(request.getParentId()) : null;
        CategoryId categoryId = categoryService.createCategory(
                request.getName(),
                request.getDescription(),
                parentId
        );
        return ResponseEntity.ok(categoryId.getValue());
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<Void> updateCategory(
            @PathVariable String categoryId,
            @RequestBody UpdateCategoryRequest request) {
        categoryService.updateCategory(
                new CategoryId(categoryId),
                request.getName(),
                request.getDescription()
        );
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{categoryId}/parent")
    public ResponseEntity<Void> changeCategoryParent(
            @PathVariable String categoryId,
            @RequestBody ChangeCategoryParentRequest request) {
        CategoryId newParentId = request.getNewParentId() != null ? new CategoryId(request.getNewParentId()) : null;
        categoryService.changeCategoryParent(new CategoryId(categoryId), newParentId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{categoryId}/display-order")
    public ResponseEntity<Void> changeCategoryDisplayOrder(
            @PathVariable String categoryId,
            @RequestBody ChangeCategoryDisplayOrderRequest request) {
        categoryService.changeCategoryDisplayOrder(new CategoryId(categoryId), request.getDisplayOrder());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{categoryId}/hide")
    public ResponseEntity<Void> hideCategory(@PathVariable String categoryId) {
        categoryService.hideCategory(new CategoryId(categoryId));
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{categoryId}/show")
    public ResponseEntity<Void> showCategory(@PathVariable String categoryId) {
        categoryService.showCategory(new CategoryId(categoryId));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable String categoryId) {
        categoryService.deleteCategory(new CategoryId(categoryId));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> getCategory(@PathVariable String categoryId) {
        Category category = categoryService.findCategoryById(new CategoryId(categoryId));
        return ResponseEntity.ok(category);
    }

    @GetMapping("/root")
    public ResponseEntity<List<Category>> getRootCategories() {
        List<Category> categories = categoryService.findRootCategories();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{categoryId}/children")
    public ResponseEntity<List<Category>> getSubCategories(@PathVariable String categoryId) {
        List<Category> categories = categoryService.findSubCategories(new CategoryId(categoryId));
        return ResponseEntity.ok(categories);
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.findAllCategories();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/visible")
    public ResponseEntity<List<Category>> getVisibleCategories() {
        List<Category> categories = categoryService.findVisibleCategories();
        return ResponseEntity.ok(categories);
    }
}