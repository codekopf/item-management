package com.codekopf.itemmanagement.interfaces.dto;

import com.codekopf.itemmanagement.domain.model.Category;
import lombok.val;
import org.junit.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoryDTOTest {

    public static final UUID CATEGORY_RANDOM_UUID = UUID.randomUUID();
    public static final String CATEGORY_NAME = "new category";

    @Test
    public void testCategoryDTOConstructor() {
        // Arrange
        // Act
        val categoryDTO = new CategoryDTO(CATEGORY_RANDOM_UUID, CATEGORY_NAME);
        // Assert
        assertAll(
                () -> assertEquals(CATEGORY_RANDOM_UUID, categoryDTO.id()),
                () -> assertEquals(CATEGORY_NAME, categoryDTO.name())
        );
    }

    @Test
    public void testStaticConstructor() {
        // Arrange
        val category = new Category(CATEGORY_RANDOM_UUID, CATEGORY_NAME);
        // Act
        val categoryDTO = CategoryDTO.of(category);
        // Assert
        assertEquals(CATEGORY_RANDOM_UUID, categoryDTO.id());
        assertEquals(CATEGORY_NAME, categoryDTO.name());
    }

}
