package com.codekopf.itemmanagement.interfaces;

import com.codekopf.itemmanagement.domain.Colour;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public final class ColourDTO {

    private UUID id;

    private String name;

    public static ColourDTO of(final Colour colour) {
        return new ColourDTO(colour.getId(), colour.getName());
    }

    public static Colour convertToDomainObject(final ColourDTO colourDTO) {
        return new Colour(colourDTO.getId(), colourDTO.getName());
    }
}
