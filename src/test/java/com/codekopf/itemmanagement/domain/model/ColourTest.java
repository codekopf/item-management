package com.codekopf.itemmanagement.domain.model;

import lombok.val;
import org.junit.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ColourTest {

    public static final UUID COLOUR_RANDOM_UUID = UUID.randomUUID();
    public static final String COLOUR_NAME = "colour name";

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
