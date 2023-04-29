package com.codekopf.itemmanagement.infrastructure.repository;

import com.codekopf.itemmanagement.domain.model.Category;
import com.codekopf.itemmanagement.infrastructure.entity.CategoryEntity;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@Tag("integration")
@SpringBootTest
public class CategoryRepositoryIntegrationTest {

    public static final UUID CATEGORY_RANDOM_UUID = UUID.randomUUID();
    public static final String CATEGORY_NAME = "new category";

    public static final UUID OTHER_CATEGORY_RANDOM_UUID = UUID.randomUUID();
    public static final String OTHER_CATEGORY_NAME = "other new category";

    @Autowired
    private CategoryRepository categoryRepository;

    @BeforeEach
    public void setUp() {
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
        Assertions.assertNotNull(savedCategory.id());
        Assertions.assertNotEquals(category.id(), savedCategory.id());
        Assertions.assertEquals(category.name(), savedCategory.name());
    }

    @Test
    public void testUpdate() {
        // Arrange
        val category = new Category(null, CATEGORY_NAME);
        val categoryEntity = new CategoryEntity(category);
        val savedCategoryEntity = categoryRepository.save(categoryEntity);

        val savedCategory = savedCategoryEntity.convertToDomainObject();
        val otherCategory = new Category(savedCategory.id(), OTHER_CATEGORY_NAME);
        val otherCategoryEntity = new CategoryEntity(otherCategory);
        // Act
        val savedOtherCategoryEntity = categoryRepository.save(otherCategoryEntity);
        // Assert
        val savedOtherCategory = savedOtherCategoryEntity.convertToDomainObject();
        Assertions.assertEquals(savedOtherCategory.id(), savedCategory.id());
        Assertions.assertEquals(savedOtherCategory.name(), OTHER_CATEGORY_NAME);
    }

    @Test
    public void testFindById() {
        // Arrange
        val category = new Category(CATEGORY_RANDOM_UUID, CATEGORY_NAME);
        val categoryEntity = new CategoryEntity(category);

        val savedCategoryEntity = categoryRepository.save(categoryEntity);
        val savedCategory = savedCategoryEntity.convertToDomainObject();
        // Act
        val maybeCategoryEntity = categoryRepository.findById(savedCategory.id());
        // Assert
        Assertions.assertTrue(maybeCategoryEntity.isPresent());
        val retrievedCategoryEntity = maybeCategoryEntity.get();
        val retrievedCategory = retrievedCategoryEntity.convertToDomainObject();
        Assertions.assertEquals(savedCategory.id(), retrievedCategory.id());
        Assertions.assertEquals(savedCategory.name(), retrievedCategory.name());
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
