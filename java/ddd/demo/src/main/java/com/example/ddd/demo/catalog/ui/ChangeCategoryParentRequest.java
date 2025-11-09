package com.example.ddd.demo.catalog.ui;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangeCategoryParentRequest {
    private String newParentId;
}