package com.yggdrasil.service;

import com.yggdrasil.databaseInterface.CategoryDatabase;
import com.yggdrasil.model.Category;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class CategoryServiceUnitTest {

    @Mock
    private CategoryDatabase categoryDatabase;

    @Mock
    private CategoryService categoryService;

    @BeforeEach
    void setUp() {
        categoryService = new CategoryService(categoryDatabase);
    }

    @AfterEach
    void tearDown() {
        categoryDatabase.deleteAll();
    }

    @Test
    void addCategory() {
        Category category = new Category(1L, "Bursztynowe");

        categoryService.addCategory(category);

        ArgumentCaptor<Category> categoryArgumentCaptor = ArgumentCaptor.forClass(Category.class);

        Mockito.verify(categoryDatabase).save(categoryArgumentCaptor.capture());

        Category capturedCategory = categoryArgumentCaptor.getValue();

        Assertions.assertEquals(capturedCategory, category);
    }

    @Test
    void editCategory() {
        Category category = new Category(1L, "Bursztynowe");
        Category categoryEdited = new Category(1L, "Amarantowe");

        Mockito.when(categoryDatabase.findById(category.getId())).thenReturn(Optional.of(category));

        categoryService.editCategory(category.getId(), categoryEdited);

        ArgumentCaptor<Category> categoryArgumentCaptor = ArgumentCaptor.forClass(Category.class);
        Mockito.verify(categoryDatabase).save(categoryArgumentCaptor.capture());

        Category capturedCategory = categoryArgumentCaptor.getValue();

        Assertions.assertNotEquals(capturedCategory.getCategoryName(), "Bursztynowe");
    }

    @Test
    void deleteCategory() {
        Category category = new Category(1L, "Bursztynowe");

        Mockito.when(categoryDatabase.save(category)).thenReturn(category);

        categoryService.addCategory(category);

        categoryService.deleteCategory(category.getId());

        Mockito.verify(categoryDatabase).deleteById(category.getId());
    }
}