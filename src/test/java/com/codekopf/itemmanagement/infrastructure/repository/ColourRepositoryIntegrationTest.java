package com.codekopf.itemmanagement.infrastructure.repository;

import com.codekopf.itemmanagement.domain.model.Colour;
import com.codekopf.itemmanagement.infrastructure.entity.ColourEntity;
import lombok.val;
import org.junit.After;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
public class ColourRepositoryIntegrationTest {

    public static final UUID COLOUR_RANDOM_UUID = UUID.randomUUID();
    public static final String COLOUR_NAME = "new colour";

    public static final UUID OTHER_COLOUR_RANDOM_UUID = UUID.randomUUID();
    public static final String OTHER_COLOUR_NAME = "other new colour";

    @Autowired
    private ColourRepository colourRepository;

    @After
    public void cleanUp() {
        // Delete all records from the database
        colourRepository.deleteAll();
    }

    @Test
    public void testSave() {
        // Arrange
        val colour = new Colour(COLOUR_RANDOM_UUID, COLOUR_NAME);
        val colourEntity = new ColourEntity(colour);
        // Act
        val savedColourEntity = colourRepository.save(colourEntity);
        // Assert
        val savedColour = savedColourEntity.convertToDomainObject();
        Assertions.assertEquals(colour.id(), savedColour.id());
        Assertions.assertEquals(colour.name(), savedColour.name());
    }

    @Test
    public void testFindById() {
        // Arrange
        val colour = new Colour(COLOUR_RANDOM_UUID, COLOUR_NAME);
        val colourEntity = new ColourEntity(colour);
        colourRepository.save(colourEntity);
        // Act
        val maybeColourEntity = colourRepository.findById(colour.id());
        // Assert
        Assertions.assertTrue(maybeColourEntity.isPresent());
        val retrievedColourEntity = maybeColourEntity.get();
        val retrievedColour = retrievedColourEntity.convertToDomainObject();
        Assertions.assertEquals(colour.id(), retrievedColour.id());
        Assertions.assertEquals(colour.name(), retrievedColour.name());
    }

    @Test
    public void testFindAll() {
        // Arrange
        val colour = new Colour(COLOUR_RANDOM_UUID, COLOUR_NAME);
        val colourEntity = new ColourEntity(colour);
        colourRepository.save(colourEntity);

        val otherColour = new Colour(OTHER_COLOUR_RANDOM_UUID, OTHER_COLOUR_NAME);
        val otherColourEntity = new ColourEntity(otherColour);
        colourRepository.save(otherColourEntity);
        // Act
        val allColours = colourRepository.findAll();
        // Assert
        Assertions.assertEquals(2, allColours.size());
    }

    // TODO test case - what if I save twice the same - should be only one

    @Test
    public void testDeleteById() {
        // Arrange
        val colour = new Colour(COLOUR_RANDOM_UUID, COLOUR_NAME);
        val colourEntity = new ColourEntity(colour);
        colourRepository.save(colourEntity);
        // Act
        colourRepository.deleteById(colour.id());
        // Assert
        val maybeColourEntity = colourRepository.findById(colour.id());
        Assertions.assertFalse(maybeColourEntity.isPresent());
    }

}
