package com.yggdrasil.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Category {

    private Long id;

    private String categoryName;

    public Category(Long id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }
}
