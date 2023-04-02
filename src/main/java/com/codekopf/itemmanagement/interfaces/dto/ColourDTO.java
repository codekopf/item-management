package com.codekopf.itemmanagement.interfaces.dto;

import com.codekopf.itemmanagement.domain.model.Colour;

import java.util.UUID;

public record ColourDTO(UUID id, String name) {

    public static ColourDTO of(final Colour colour) {
        return new ColourDTO(colour.id(), colour.name());
    }

}
