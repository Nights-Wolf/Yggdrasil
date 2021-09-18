package com.yggdrasil.service;

import com.yggdrasil.DAO.CategoryDAO;
import com.yggdrasil.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryDAO categoryDAO;

    public void addCategory(Category category) {
        categoryDAO.addCategory(category);
    }

    public void editCategory(Long id, Category category) {
        categoryDAO.editCategory(id, category);
    }

    public void deleteCategory(Long id) {
        categoryDAO.deleteCategory(id);
    }
}
