package com.yggdrasil.service;

import com.yggdrasil.DAO.FakeCategoryDAO;
import com.yggdrasil.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private FakeCategoryDAO fakeCategoryDAO;

    public void addCategory(Category category) {
        fakeCategoryDAO.addCategory(category);
    }

    public void editCategory(Long id, Category category) {
        fakeCategoryDAO.editCategory(id, category);
    }

    public void deleteCategory(Long id) {
        fakeCategoryDAO.deleteCategory(id);
    }
}
