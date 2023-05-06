package com.codekopf.itemmanagement.domain.model;

import lombok.val;
import org.junit.Test;
import org.junit.jupiter.api.Tag;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("unit")
public class ColourTest {

    private static final UUID COLOUR_RANDOM_UUID = UUID.randomUUID();
    private static final String COLOUR_NAME = "colour name";

    @Test
    public void testColourConstructor() {
        // Arrange
        // Act
        val colour = new Colour(COLOUR_RANDOM_UUID, COLOUR_NAME);
        // Assert
        assertAll(
                () -> assertEquals(COLOUR_RANDOM_UUID, colour.id()),
                () -> assertEquals(COLOUR_NAME, colour.name())
        );
    }

    @Test
    public void testStaticConstructor() {
        // Arrange
        // Act
        val colour = Colour.of(COLOUR_RANDOM_UUID, COLOUR_NAME);
        // Assert
        assertEquals(COLOUR_RANDOM_UUID, colour.id());
        assertEquals(COLOUR_NAME, colour.name());
    }

}
