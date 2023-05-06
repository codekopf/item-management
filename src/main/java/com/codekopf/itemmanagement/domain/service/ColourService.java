package com.codekopf.itemmanagement.domain.service;

import com.codekopf.itemmanagement.domain.model.Colour;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ColourService {

        /**
         * Get all colours
         *
         * @return List of {@link Colour}
         */
        List<Colour> getAllColours();

        /**
         * Get {@link Colour} by id
         *
         * @param id {@link UUID} of the colour
         * @return Optional of {@link Colour}
         */
        Optional<Colour> getColourById(UUID id);

        // TODO abuday - create separate method for saving and updating
        /**
         * Create a new {@link Colour}
         *
         * @param colour {@link Colour} to save
         * @return Created {@link Colour}
         */
        Colour saveColour(Colour colour);

        /**
         * Delete {@link Colour} by id
         *
         * @param id {@link UUID} of the {@link Colour}
         */
        void deleteColour(UUID id);

}
