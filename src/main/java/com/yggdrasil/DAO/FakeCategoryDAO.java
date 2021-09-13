package com.yggdrasil.DAO;

import com.yggdrasil.databaseInterface.CategoryDatabase;
import com.yggdrasil.model.Category;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class FakeCategoryDAO implements CategoryDatabase {

    HashMap<Long, Category> fakeCategoryRepository = new HashMap<>();

    @Override
    public void addCategory(Category category) {
        fakeCategoryRepository.put(category.getId(), category);
    }

    @Override
    public void editCategory(Long id, Category category) {
        fakeCategoryRepository.replace(id, category);
    }

    @Override
    public void deleteCategory(Long id) {
        fakeCategoryRepository.remove(id);
    }
}
