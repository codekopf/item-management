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

    public static final UUID ITEM_RANDOM_UUID = UUID.randomUUID();
    public static final String ITEM_NAME = "item name";
    public static final String ITEM_DESCRIPTION = "item description";
    public static final BigDecimal PRICE = new BigDecimal("100.00");

    public static final UUID COLOUR_RANDOM_UUID = UUID.randomUUID();
    public static final String COLOUR_NAME = "colour name";
    public static final Colour ITEM_COLOUR = new Colour(COLOUR_RANDOM_UUID, COLOUR_NAME);
    public static final ColourDTO COLOUR_DTO = new ColourDTO(COLOUR_RANDOM_UUID, COLOUR_NAME);

    public static final UUID CATEGORY_RANDOM_UUID = UUID.randomUUID();
    public static final String CATEGORY_NAME = "new category";
    public static final Category ITEM_CATEGORY = new Category(CATEGORY_RANDOM_UUID, CATEGORY_NAME);
    public static final CategoryDTO CATEGORY_DTO = new CategoryDTO(CATEGORY_RANDOM_UUID, CATEGORY_NAME);

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
