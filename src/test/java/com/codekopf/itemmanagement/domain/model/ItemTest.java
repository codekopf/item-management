package com.codekopf.itemmanagement.domain.model;

import lombok.val;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemTest {

    public static final UUID ITEM_RANDOM_UUID = UUID.randomUUID();
    public static final String ITEM_NAME = "item name";
    public static final String ITEM_DESCRIPTION = "item description";
    public static final BigDecimal PRICE = new BigDecimal("100.00");

    public static final UUID COLOUR_RANDOM_UUID = UUID.randomUUID();
    public static final String COLOUR_NAME = "colour name";
    public static final Colour ITEM_COLOUR = new Colour(COLOUR_RANDOM_UUID, COLOUR_NAME);

    public static final UUID CATEGORY_RANDOM_UUID = UUID.randomUUID();
    public static final String CATEGORY_NAME = "new category";
    public static final Category ITEM_CATEGORY = new Category(CATEGORY_RANDOM_UUID, CATEGORY_NAME);

    @Test
    public void testItemConstructor() {
        // Arrange
        // Act
        val item = new Item(ITEM_RANDOM_UUID, ITEM_NAME, ITEM_DESCRIPTION, PRICE, ITEM_COLOUR, ITEM_CATEGORY);
        // Assert
        assertAll(
                () -> assertEquals(ITEM_RANDOM_UUID, item.id()),
                () -> assertEquals(ITEM_NAME, item.name()),
                () -> assertEquals(ITEM_DESCRIPTION, item.description()),
                () -> assertEquals(PRICE, item.price()),
                () -> assertEquals(COLOUR_RANDOM_UUID, item.colour().id()),
                () -> assertEquals(COLOUR_NAME, item.colour().name()),
                () -> assertEquals(CATEGORY_RANDOM_UUID, item.category().id()),
                () -> assertEquals(CATEGORY_NAME, item.category().name())
        );
    }

    @Test
    public void testStaticConstructor() {
        // Arrange
        // Act
        val item = Item.of(ITEM_RANDOM_UUID, ITEM_NAME, ITEM_DESCRIPTION, PRICE, ITEM_COLOUR, ITEM_CATEGORY);
        // Assert
        assertEquals(ITEM_RANDOM_UUID, item.id());
        assertEquals(ITEM_NAME, item.name());
        assertEquals(ITEM_DESCRIPTION, item.description());
        assertEquals(PRICE, item.price());

        assertEquals(COLOUR_RANDOM_UUID, item.colour().id());
        assertEquals(COLOUR_NAME, item.colour().name());

        assertEquals(CATEGORY_RANDOM_UUID, item.category().id());
        assertEquals(CATEGORY_NAME, item.category().name());
    }

}
