package com.yggdrasil.databaseInterface;

import com.yggdrasil.model.Category;

public interface CategoryDatabase {

    void addCategory(Category category);
    void editCategory(Long id, Category category);
    void deleteCategory(Long id);
}
