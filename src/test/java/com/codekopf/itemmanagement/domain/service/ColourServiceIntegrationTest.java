package com.codekopf.itemmanagement.domain.service;

import com.codekopf.itemmanagement.domain.model.Colour;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Tag("integration")
@SpringBootTest
public class ColourServiceIntegrationTest {

    public static final String COLOUR_NAME = "new colour";

    @Autowired
    private ColourService colourService;

    @BeforeEach
    public void setUp() {
        // Delete all records from the database
        colourService.getAllColours().forEach(colour -> colourService.deleteColour(colour.id()));
    }

    @Test
    public void testGetAllColours() {
        // Arrange
        var colours = colourService.getAllColours();
        Assertions.assertEquals(0, colours.size());
        val colour = new Colour(null, COLOUR_NAME);
        // Act
        colourService.saveColour(colour);
        // Assert
        colours = colourService.getAllColours();
        Assertions.assertEquals(1, colours.size());
    }

    @Test
    public void testGetColourById() {
        // Arrange
        val colour = new Colour(null, COLOUR_NAME);
        val savedColour = colourService.saveColour(colour);
        // Act
        val maybeColour = colourService.getColourById(savedColour.id());
        // Assert
        Assertions.assertTrue(maybeColour.isPresent());
        val retrievedColour = maybeColour.get();
        Assertions.assertEquals(savedColour.id(), retrievedColour.id());
        Assertions.assertEquals(savedColour.name(), retrievedColour.name());
    }

    @Test
    public void testSaveColour() {
        // Arrange
        val colour = new Colour(null, COLOUR_NAME);
        // Act
        val savedColour = colourService.saveColour(colour);
        // Assert
        Assertions.assertNotNull(savedColour.id());
        Assertions.assertNotEquals(colour.id(), savedColour.id());
        Assertions.assertEquals(colour.name(), savedColour.name());
    }

    @Test
    public void testDeleteColour() {
        // Arrange
        val colour = new Colour(null, COLOUR_NAME);
        val savedColour = colourService.saveColour(colour);
        // Act
        colourService.deleteColour(savedColour.id());
        // Assert
        val maybeColour = colourService.getColourById(savedColour.id());
        Assertions.assertFalse(maybeColour.isPresent());
    }

}
