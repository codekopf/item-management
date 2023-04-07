package com.codekopf.itemmanagement.interfaces.dto;

import lombok.val;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class IncomingItemDTOTest {

    public static final String ITEM_NAME = "item name";
    public static final String ITEM_DESCRIPTION = "item description";
    public static final BigDecimal PRICE = new BigDecimal("100.00");
    public static final UUID COLOUR_RANDOM_UUID = UUID.randomUUID();
    public static final UUID CATEGORY_RANDOM_UUID = UUID.randomUUID();

    @Test
    public void testIncomingItemDTOConstructor() {

        // Arrange
        // Act
        val incomingItemDTO = new IncomingItemDTO(ITEM_NAME, ITEM_DESCRIPTION, PRICE, COLOUR_RANDOM_UUID, CATEGORY_RANDOM_UUID);
        // Assert
        assertAll(
                () -> assertEquals(ITEM_NAME, incomingItemDTO.name()),
                () -> assertEquals(ITEM_DESCRIPTION, incomingItemDTO.description()),
                () -> assertEquals(PRICE, incomingItemDTO.price()),
                () -> assertEquals(COLOUR_RANDOM_UUID, incomingItemDTO.colourId()),
                () -> assertEquals(CATEGORY_RANDOM_UUID, incomingItemDTO.categoryId())
        );
    }

}
