package com.example.ddd.demo.catalog.application;

import com.example.ddd.demo.catalog.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public ProductId createProduct(String name, String description, BigDecimal price,
                                   Integer stockQuantity, CategoryId categoryId) {
        Category category = findCategoryById(categoryId);

        if (productRepository.existsByName(name)) {
            throw new IllegalArgumentException("이미 존재하는 상품명입니다: " + name);
        }

        Product product = new Product(name, description, price, stockQuantity, category);
        productRepository.save(product);
        return product.getId();
    }

    public void updateProduct(ProductId productId, String name, String description,
                              BigDecimal price, CategoryId categoryId) {
        Product product = findProductById(productId);

        if (!product.getName().equals(name) && productRepository.existsByName(name)) {
            throw new IllegalArgumentException("이미 존재하는 상품명입니다: " + name);
        }

        Category category = findCategoryById(categoryId);

        product.changeName(name);
        product.changeDescription(description);
        product.changePrice(price);
        product.changeCategory(category);
    }

    public void addStock(ProductId productId, int quantity) {
        Product product = findProductById(productId);
        product.addStock(quantity);
    }

    public void removeStock(ProductId productId, int quantity) {
        Product product = findProductById(productId);
        product.removeStock(quantity);
    }

    public void makeProductAvailable(ProductId productId) {
        Product product = findProductById(productId);
        product.makeAvailable();
    }

    public void discontinueProduct(ProductId productId) {
        Product product = findProductById(productId);
        product.discontinue();
    }

    public void hideProduct(ProductId productId) {
        Product product = findProductById(productId);
        product.hide();
    }

    public void showProduct(ProductId productId) {
        Product product = findProductById(productId);
        product.show();
    }

    public void deleteProduct(ProductId productId) {
        Product product = findProductById(productId);
        productRepository.delete(product);
    }

    @Transactional(readOnly = true)
    public Product findProductById(ProductId productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다: " + productId));
    }

    @Transactional(readOnly = true)
    public List<Product> findProductsByCategory(CategoryId categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

    @Transactional(readOnly = true)
    public List<Product> findAvailableProducts() {
        return productRepository.findAvailableProducts();
    }

    @Transactional(readOnly = true)
    public List<Product> findProductsByStatus(ProductStatus status) {
        return productRepository.findByStatus(status);
    }

    @Transactional(readOnly = true)
    public List<Product> findProductsByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }

    @Transactional(readOnly = true)
    public List<Product> searchProducts(String keyword) {
        return productRepository.findByNameContaining(keyword);
    }

    @Transactional(readOnly = true)
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Transactional(readOnly = true)
    public boolean canOrderProduct(ProductId productId, int quantity) {
        Product product = findProductById(productId);
        return product.canOrder(quantity);
    }

    private Category findCategoryById(CategoryId categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("카테고리를 찾을 수 없습니다: " + categoryId));
    }
}