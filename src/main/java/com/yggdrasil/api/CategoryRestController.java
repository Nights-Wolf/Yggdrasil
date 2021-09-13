package com.yggdrasil.api;

import com.yggdrasil.model.Category;
import com.yggdrasil.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
public class CategoryRestController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public void addCategory(@RequestBody Category category) {
        categoryService.addCategory(category);
    }

    @PutMapping("{id}")
    public void editCategory(@PathVariable("id") Long id, @RequestBody Category category) {
        categoryService.editCategory(id, category);
    }

    @DeleteMapping("{id}")
    public void deleteCategory(@PathVariable("id") Long id) {
        categoryService.deleteCategory(id);
    }
}
