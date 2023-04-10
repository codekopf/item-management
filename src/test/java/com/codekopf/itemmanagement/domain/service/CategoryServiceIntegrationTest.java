package com.codekopf.itemmanagement.domain.service;

import com.codekopf.itemmanagement.domain.model.Category;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
public class CategoryServiceIntegrationTest {

    public static final UUID CATEGORY_RANDOM_UUID = UUID.randomUUID();
    public static final String CATEGORY_NAME = "new category";

    @Autowired
    private CategoryService categoryService;



    @Test
    public void testGetAllCategories() {
        // Arrange
        var categories = categoryService.getAllCategories();
        Assertions.assertEquals(0, categories.size());
        val category = new Category(CATEGORY_RANDOM_UUID, CATEGORY_NAME);
        // Act
        categoryService.saveCategory(category);
        // Assert
        categories = categoryService.getAllCategories();
        Assertions.assertEquals(1, categories.size());
    }

    @Test
    public void testGetCategoryById() {
        // Arrange
        val category = new Category(CATEGORY_RANDOM_UUID, CATEGORY_NAME);
        categoryService.saveCategory(category);
        // Act
        val maybeCategory = categoryService.getCategoryById(category.id());
        // Assert
        Assertions.assertTrue(maybeCategory.isPresent());
        val retrievedCategory = maybeCategory.get();
        Assertions.assertEquals(category.id(), retrievedCategory.id());
        Assertions.assertEquals(category.name(), retrievedCategory.name());
    }

    @Test
    public void testSaveCategory() {
        // Arrange
        val category = new Category(CATEGORY_RANDOM_UUID, CATEGORY_NAME);
        // Act
        val savedCategory = categoryService.saveCategory(category);
        // Assert
        Assertions.assertEquals(category.id(), savedCategory.id());
        Assertions.assertEquals(category.name(), savedCategory.name());
    }

    @Test
    public void testDeleteCategory() {
        // Arrange
        val category = new Category(CATEGORY_RANDOM_UUID, CATEGORY_NAME);
        categoryService.saveCategory(category);
        // Act
        categoryService.deleteCategory(category.id());
        // Assert
        val maybeCategory = categoryService.getCategoryById(category.id());
        Assertions.assertFalse(maybeCategory.isPresent());
    }
}
