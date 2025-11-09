package com.example.ddd.demo.catalog.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {
    @EmbeddedId
    private CategoryId id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Category> children = new ArrayList<>();

    @Column(name = "display_order")
    private Integer displayOrder;

    @Column(name = "visible")
    private boolean visible = true;

    public Category(String name, String description, Category parent) {
        validateCategory(name);

        this.id = CategoryId.generate();
        this.name = name;
        this.description = description;
        this.parent = parent;
        this.displayOrder = 0;

        if (parent != null) {
            parent.addChild(this);
        }
    }

    public Category(String name, String description) {
        this(name, description, null);
    }

    private void validateCategory(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("카테고리명은 필수입니다.");
        }
    }

    public void changeName(String name) {
        validateCategory(name);
        this.name = name;
    }

    public void changeDescription(String description) {
        this.description = description;
    }

    public void changeParent(Category newParent) {
        if (newParent != null && newParent.equals(this)) {
            throw new IllegalArgumentException("자기 자신을 부모로 설정할 수 없습니다.");
        }

        if (isDescendantOf(newParent)) {
            throw new IllegalArgumentException("하위 카테고리를 부모로 설정할 수 없습니다.");
        }

        if (this.parent != null) {
            this.parent.removeChild(this);
        }

        this.parent = newParent;
        if (newParent != null) {
            newParent.addChild(this);
        }
    }

    public void changeDisplayOrder(Integer displayOrder) {
        if (displayOrder == null || displayOrder < 0) {
            throw new IllegalArgumentException("표시 순서는 0 이상이어야 합니다.");
        }
        this.displayOrder = displayOrder;
    }

    public void hide() {
        this.visible = false;
    }

    public void show() {
        this.visible = true;
    }

    private void addChild(Category child) {
        if (!children.contains(child)) {
            children.add(child);
        }
    }

    private void removeChild(Category child) {
        children.remove(child);
    }

    private boolean isDescendantOf(Category category) {
        if (category == null) {
            return false;
        }

        for (Category child : this.children) {
            if (child.equals(category) || child.isDescendantOf(category)) {
                return true;
            }
        }
        return false;
    }

    public boolean isRoot() {
        return parent == null;
    }

    public boolean isLeaf() {
        return children.isEmpty();
    }

    public int getDepth() {
        if (isRoot()) {
            return 0;
        }
        return parent.getDepth() + 1;
    }

    public String getFullPath() {
        if (isRoot()) {
            return name;
        }
        return parent.getFullPath() + " > " + name;
    }
}