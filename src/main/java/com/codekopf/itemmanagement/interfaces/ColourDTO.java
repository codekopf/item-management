package com.codekopf.itemmanagement.interfaces;

import com.codekopf.itemmanagement.domain.Colour;

import java.util.UUID;

public record ColourDTO(UUID id, String name) {

    public static ColourDTO of(final Colour colour) {
        return new ColourDTO(colour.id(), colour.name());
    }

    public static Colour convertToDomainObject(final ColourDTO colourDTO) {
        return new Colour(colourDTO.id(), colourDTO.name());
    }

}
