package com.codekopf.itemmanagement.infrastructure.repository;

import com.codekopf.itemmanagement.domain.model.Category;
import com.codekopf.itemmanagement.infrastructure.entity.CategoryEntity;
import lombok.val;
import org.junit.After;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
public class CategoryRepositoryIntegrationTest {

    public static final UUID CATEGORY_RANDOM_UUID = UUID.randomUUID();
    public static final String CATEGORY_NAME = "new category";

    public static final UUID OTHER_CATEGORY_RANDOM_UUID = UUID.randomUUID();
    public static final String OTHER_CATEGORY_NAME = "other new category";

    @Autowired
    private CategoryRepository categoryRepository;

    @After
    public void cleanUp() {
        // Delete all records from the database
        categoryRepository.deleteAll();
    }

    @Test
    public void testSave() {
        // Arrange
        val category = new Category(CATEGORY_RANDOM_UUID, CATEGORY_NAME);
        val categoryEntity = new CategoryEntity(category);
        // Act
        val savedCategoryEntity = categoryRepository.save(categoryEntity);
        // Assert
        val savedCategory = savedCategoryEntity.convertToDomainObject();
        Assertions.assertEquals(category.id(), savedCategory.id());
        Assertions.assertEquals(category.name(), savedCategory.name());
    }

    @Test
    public void testFindById() {
        // Arrange
        val category = new Category(CATEGORY_RANDOM_UUID, CATEGORY_NAME);
        val categoryEntity = new CategoryEntity(category);
        categoryRepository.save(categoryEntity);
        // Act
        val maybeCategoryEntity = categoryRepository.findById(category.id());
        // Assert
        Assertions.assertTrue(maybeCategoryEntity.isPresent());
        val retrievedCategoryEntity = maybeCategoryEntity.get();
        val retrievedCategory = retrievedCategoryEntity.convertToDomainObject();
        Assertions.assertEquals(category.id(), retrievedCategory.id());
        Assertions.assertEquals(category.name(), retrievedCategory.name());
    }

    @Test
    public void testFindAll() {
        // Arrange
        val category = new Category(CATEGORY_RANDOM_UUID, CATEGORY_NAME);
        val categoryEntity = new CategoryEntity(category);
        categoryRepository.save(categoryEntity);

        val otherCategory = new Category(OTHER_CATEGORY_RANDOM_UUID, OTHER_CATEGORY_NAME);
        val otherCategoryEntity = new CategoryEntity(otherCategory);
        categoryRepository.save(otherCategoryEntity);
        // Act
        val allCategories = categoryRepository.findAll();
        // Assert
        Assertions.assertEquals(2, allCategories.size());
    }

    // TODO test case - what if I save twice the same - should be only one

    @Test
    public void testDeleteById() {
        // Arrange
        val category = new Category(CATEGORY_RANDOM_UUID, CATEGORY_NAME);
        val categoryEntity = new CategoryEntity(category);
        categoryRepository.save(categoryEntity);
        // Act
        categoryRepository.deleteById(category.id());
        // Assert
        val maybeCategoryEntity = categoryRepository.findById(category.id());
        Assertions.assertFalse(maybeCategoryEntity.isPresent());
    }

}
