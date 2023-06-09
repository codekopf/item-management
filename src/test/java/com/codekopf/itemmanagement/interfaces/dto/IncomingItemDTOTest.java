package com.codekopf.itemmanagement.interfaces.dto;

import lombok.val;
import org.junit.Test;
import org.junit.jupiter.api.Tag;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("unit")
public class IncomingItemDTOTest {

    private static final String ITEM_NAME = "item name";
    private static final String ITEM_DESCRIPTION = "item description";
    private static final BigDecimal PRICE = new BigDecimal("100.00");
    private static final UUID COLOUR_RANDOM_UUID = UUID.randomUUID();
    private static final UUID CATEGORY_RANDOM_UUID = UUID.randomUUID();

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
