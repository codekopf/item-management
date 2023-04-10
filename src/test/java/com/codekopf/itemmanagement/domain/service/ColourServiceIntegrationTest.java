package com.codekopf.itemmanagement.domain.service;

import com.codekopf.itemmanagement.domain.model.Colour;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
public class ColourServiceIntegrationTest {

    public static final UUID COLOUR_RANDOM_UUID = UUID.randomUUID();
    public static final String COLOUR_NAME = "new colour";

    @Autowired
    private ColourService colourService;



    @Test
    public void testGetAllColours() {
        // Arrange
        var colours = colourService.getAllColours();
        Assertions.assertEquals(0, colours.size());
        val colour = new Colour(COLOUR_RANDOM_UUID, COLOUR_NAME);
        // Act
        colourService.saveColour(colour);
        // Assert
        colours = colourService.getAllColours();
        Assertions.assertEquals(1, colours.size());
    }

    @Test
    public void testGetColourById() {
        // Arrange
        val colour = new Colour(COLOUR_RANDOM_UUID, COLOUR_NAME);
        colourService.saveColour(colour);
        // Act
        val maybeColour = colourService.getColourById(colour.id());
        // Assert
        Assertions.assertTrue(maybeColour.isPresent());
        val retrievedColour = maybeColour.get();
        Assertions.assertEquals(colour.id(), retrievedColour.id());
        Assertions.assertEquals(colour.name(), retrievedColour.name());
    }

    @Test
    public void testSaveColour() {
        // Arrange
        val colour = new Colour(COLOUR_RANDOM_UUID, COLOUR_NAME);
        // Act
        val savedColour = colourService.saveColour(colour);
        // Assert
        Assertions.assertEquals(colour.id(), savedColour.id());
        Assertions.assertEquals(colour.name(), savedColour.name());
    }

    @Test
    public void testDeleteColour() {
        // Arrange
        val colour = new Colour(COLOUR_RANDOM_UUID, COLOUR_NAME);
        colourService.saveColour(colour);
        // Act
        colourService.deleteColour(colour.id());
        // Assert
        val maybeColour = colourService.getColourById(colour.id());
        Assertions.assertFalse(maybeColour.isPresent());
    }
}
