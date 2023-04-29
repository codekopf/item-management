package com.codekopf.itemmanagement.domain.model;

import com.codekopf.itemmanagement.interfaces.dto.CategoryDTO;
import lombok.val;
import org.junit.Test;
import org.junit.jupiter.api.Tag;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("unit")
public class CategoryTest {

    public static final UUID CATEGORY_RANDOM_UUID = UUID.randomUUID();
    public static final String CATEGORY_NAME = "new category";

    @Test
    public void testCategoryConstructor() {
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
        // Act
        val category = Category.of(CATEGORY_RANDOM_UUID, CATEGORY_NAME);
        // Assert
        assertEquals(CATEGORY_RANDOM_UUID, category.id());
        assertEquals(CATEGORY_NAME, category.name());
    }

}
