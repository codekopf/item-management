package com.codekopf.itemmanagement.interfaces.dto;

import com.codekopf.itemmanagement.domain.model.Category;
import com.codekopf.itemmanagement.domain.model.Colour;
import com.codekopf.itemmanagement.domain.model.Item;
import lombok.val;
import org.junit.Test;
import org.junit.jupiter.api.Tag;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("unit")
public class OutgoingItemDTOTest {

    private static final UUID ITEM_RANDOM_UUID = UUID.randomUUID();
    private static final String ITEM_NAME = "item name";
    private static final String ITEM_DESCRIPTION = "item description";
    private static final BigDecimal PRICE = new BigDecimal("100.00");

    private static final UUID COLOUR_RANDOM_UUID = UUID.randomUUID();
    private static final String COLOUR_NAME = "colour name";
    private static final Colour ITEM_COLOUR = new Colour(COLOUR_RANDOM_UUID, COLOUR_NAME);
    private static final ColourDTO COLOUR_DTO = new ColourDTO(COLOUR_RANDOM_UUID, COLOUR_NAME);

    private static final UUID CATEGORY_RANDOM_UUID = UUID.randomUUID();
    private static final String CATEGORY_NAME = "new category";
    private static final Category ITEM_CATEGORY = new Category(CATEGORY_RANDOM_UUID, CATEGORY_NAME);
    private static final CategoryDTO CATEGORY_DTO = new CategoryDTO(CATEGORY_RANDOM_UUID, CATEGORY_NAME);

    @Test
    public void testOutgoingItemDTOConstructor() {
        // Arrange
        // Act
        val outgoingItemDTO = new OutgoingItemDTO(ITEM_RANDOM_UUID, ITEM_NAME, ITEM_DESCRIPTION, PRICE, COLOUR_DTO, CATEGORY_DTO);
        // Assert
        assertAll(
                () -> assertEquals(ITEM_RANDOM_UUID, outgoingItemDTO.id()),
                () -> assertEquals(ITEM_NAME, outgoingItemDTO.name()),
                () -> assertEquals(ITEM_DESCRIPTION, outgoingItemDTO.description()),
                () -> assertEquals(PRICE, outgoingItemDTO.price()),
                () -> assertEquals(COLOUR_RANDOM_UUID, outgoingItemDTO.colourDTO().id()),
                () -> assertEquals(COLOUR_NAME, outgoingItemDTO.colourDTO().name()),
                () -> assertEquals(CATEGORY_RANDOM_UUID, outgoingItemDTO.categoryDTO().id()),
                () -> assertEquals(CATEGORY_NAME, outgoingItemDTO.categoryDTO().name())
        );
    }

    @Test
    public void testStaticConstructor() {
        // Arrange
        val item = new Item(ITEM_RANDOM_UUID, ITEM_NAME, ITEM_DESCRIPTION, PRICE, ITEM_COLOUR, ITEM_CATEGORY);
        // Act
        val outgoingItemDTO = OutgoingItemDTO.of(item);
        // Assert
        assertEquals(ITEM_RANDOM_UUID, outgoingItemDTO.id());
        assertEquals(ITEM_NAME, outgoingItemDTO.name());
        assertEquals(ITEM_DESCRIPTION, outgoingItemDTO.description());
        assertEquals(PRICE, outgoingItemDTO.price());

        assertEquals(COLOUR_RANDOM_UUID, outgoingItemDTO.colourDTO().id());
        assertEquals(COLOUR_NAME, outgoingItemDTO.colourDTO().name());

        assertEquals(CATEGORY_RANDOM_UUID, outgoingItemDTO.categoryDTO().id());
        assertEquals(CATEGORY_NAME, outgoingItemDTO.categoryDTO().name());
    }

}
