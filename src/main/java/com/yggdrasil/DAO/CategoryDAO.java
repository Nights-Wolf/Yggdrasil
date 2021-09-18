package com.yggdrasil.DAO;

import com.yggdrasil.databaseInterface.CategoryDatabase;
import com.yggdrasil.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryDAO {

    @Autowired
    private final CategoryDatabase categoryDatabase;

    public CategoryDAO(CategoryDatabase categoryDatabase) {
        this.categoryDatabase = categoryDatabase;
    }

    public void addCategory(Category category) {
        categoryDatabase.save(category);
    }

    public void editCategory(Long id, Category category) {
    }

    public void deleteCategory(Long id) {
        categoryDatabase.deleteById(id);
    }
}
