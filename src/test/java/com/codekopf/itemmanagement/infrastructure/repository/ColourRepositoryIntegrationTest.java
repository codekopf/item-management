package com.codekopf.itemmanagement.infrastructure.repository;

import com.codekopf.itemmanagement.domain.model.Colour;
import com.codekopf.itemmanagement.infrastructure.entity.ColourEntity;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@Tag("integration")
@SpringBootTest
public class ColourRepositoryIntegrationTest {

    private static final UUID COLOUR_RANDOM_UUID = UUID.randomUUID();
    private static final String COLOUR_NAME = "new colour";

    private static final UUID OTHER_COLOUR_RANDOM_UUID = UUID.randomUUID();
    public static final String OTHER_COLOUR_NAME = "other new colour";

    @Autowired
    private ColourRepository colourRepository;

    @BeforeEach
    public void setUp() {
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
        Assertions.assertNotNull(savedColour.id());
        Assertions.assertNotEquals(colour.id(), savedColour.id());
        Assertions.assertEquals(colour.name(), savedColour.name());
    }

    @Test
    public void testUpdate() {
        // Arrange
        val colour = new Colour(null, COLOUR_NAME);
        val colourEntity = new ColourEntity(colour);
        val savedColourEntity = colourRepository.save(colourEntity);
        val savedColour = savedColourEntity.convertToDomainObject();
        val otherColour = new Colour(savedColour.id(), OTHER_COLOUR_NAME);
        val otherColourEntity = new ColourEntity(otherColour);
        // Act
        val savedOtherColourEntity = colourRepository.save(otherColourEntity);
        // Assert
        val savedOtherColour = savedOtherColourEntity.convertToDomainObject();
        Assertions.assertEquals(savedOtherColour.id(), savedColour.id());
        Assertions.assertEquals(savedOtherColour.name(), OTHER_COLOUR_NAME);
    }

    @Test
    public void testFindById() {
        // Arrange
        val colour = new Colour(COLOUR_RANDOM_UUID, COLOUR_NAME);
        val colourEntity = new ColourEntity(colour);

        val savedColourEntity = colourRepository.save(colourEntity);
        val savedColour = savedColourEntity.convertToDomainObject();
        // Act
        val maybeColourEntity = colourRepository.findById(savedColour.id());
        // Assert
        Assertions.assertTrue(maybeColourEntity.isPresent());
        val retrievedColourEntity = maybeColourEntity.get();
        val retrievedColour = retrievedColourEntity.convertToDomainObject();
        Assertions.assertEquals(savedColour.id(), retrievedColour.id());
        Assertions.assertEquals(savedColour.name(), retrievedColour.name());
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
