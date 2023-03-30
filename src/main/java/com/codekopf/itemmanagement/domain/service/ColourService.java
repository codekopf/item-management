package com.codekopf.itemmanagement.domain.service;

import com.codekopf.itemmanagement.domain.model.Colour;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ColourService {
        List<Colour> getAllColours();
        Optional<Colour> getColourById(UUID id);
        Colour saveColour(Colour colour);
        void deleteColour(UUID id);

}
