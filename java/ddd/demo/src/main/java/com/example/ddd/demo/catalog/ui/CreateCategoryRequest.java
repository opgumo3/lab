package com.example.ddd.demo.catalog.ui;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCategoryRequest {
    private String name;
    private String description;
    private String parentId;
}