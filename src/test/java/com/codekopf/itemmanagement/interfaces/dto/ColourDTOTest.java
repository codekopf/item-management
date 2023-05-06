package com.codekopf.itemmanagement.interfaces.dto;

import com.codekopf.itemmanagement.domain.model.Colour;
import lombok.val;
import org.junit.Test;
import org.junit.jupiter.api.Tag;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("unit")
public class ColourDTOTest {

    private static final UUID COLOUR_RANDOM_UUID = UUID.randomUUID();
    private static final String COLOUR_NAME = "colour name";

    @Test
    public void testColourConstructor() {
        // Arrange
        // Act
        val colourDTO = new ColourDTO(COLOUR_RANDOM_UUID, COLOUR_NAME);
        // Assert
        assertAll(
                () -> assertEquals(COLOUR_RANDOM_UUID, colourDTO.id()),
                () -> assertEquals(COLOUR_NAME, colourDTO.name())
        );
    }

    @Test
    public void testStaticConstructor() {
        // Arrange
        val colour = new Colour(COLOUR_RANDOM_UUID, COLOUR_NAME);
        // Act
        val colourDTO = ColourDTO.of(colour);
        // Assert
        assertEquals(COLOUR_RANDOM_UUID, colourDTO.id());
        assertEquals(COLOUR_NAME, colourDTO.name());
    }

}
