package com.yggdrasil.service;

import com.yggdrasil.databaseInterface.CategoryDatabase;
import com.yggdrasil.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {


    private final CategoryDatabase categoryDatabase;

    @Autowired
    public CategoryService(CategoryDatabase categoryDatabase) {
        this.categoryDatabase = categoryDatabase;
    }

    public void addCategory(Category category) {
        categoryDatabase.save(category);
    }

    public void editCategory(Long id, Category category) {
        Category editedCategory = categoryDatabase.findById(id).orElseThrow();

        editedCategory.setCategoryName(category.getCategoryName());

        categoryDatabase.save(editedCategory);
    }

    public void deleteCategory(Long id) {
        categoryDatabase.deleteById(id);
    }
}
