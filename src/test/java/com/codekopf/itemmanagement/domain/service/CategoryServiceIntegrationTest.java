package com.codekopf.itemmanagement.domain.service;

import com.codekopf.itemmanagement.domain.model.Category;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Tag("integration")
@SpringBootTest
public class CategoryServiceIntegrationTest {

    private static final String CATEGORY_NAME = "new category";

    @Autowired
    private CategoryService categoryService;

    @BeforeEach
    public void setUp() {
        // Delete all records from the database
        categoryService.getAllCategories().forEach(category -> categoryService.deleteCategory(category.id()));
    }

    @Test
    public void testGetAllCategories() {
        // Arrange
        var categories = categoryService.getAllCategories();
        Assertions.assertEquals(0, categories.size());
        val category = new Category(null, CATEGORY_NAME);
        // Act
        categoryService.saveCategory(category);
        // Assert
        categories = categoryService.getAllCategories();
        Assertions.assertEquals(1, categories.size());
    }

    @Test
    public void testGetCategoryById() {
        // Arrange
        val category = new Category(null, CATEGORY_NAME);
        val savedCategory = categoryService.saveCategory(category);
        // Act
        val maybeCategory = categoryService.getCategoryById(savedCategory.id());
        // Assert
        Assertions.assertTrue(maybeCategory.isPresent());
        val retrievedCategory = maybeCategory.get();
        Assertions.assertEquals(savedCategory.id(), retrievedCategory.id());
        Assertions.assertEquals(savedCategory.name(), retrievedCategory.name());
    }

    @Test
    public void testSaveCategory() {
        // Arrange
        val category = new Category(null, CATEGORY_NAME);
        // Act
        val savedCategory = categoryService.saveCategory(category);
        // Assert
        Assertions.assertNotNull(savedCategory.id());
        Assertions.assertNotEquals(category.id(), savedCategory.id());
        Assertions.assertEquals(category.name(), savedCategory.name());
    }

    @Test
    public void testDeleteCategory() {
        // Arrange
        val category = new Category(null, CATEGORY_NAME);
        val savedCategory = categoryService.saveCategory(category);
        // Act
        categoryService.deleteCategory(savedCategory.id());
        // Assert
        val maybeCategory = categoryService.getCategoryById(savedCategory.id());
        Assertions.assertFalse(maybeCategory.isPresent());
    }

}
