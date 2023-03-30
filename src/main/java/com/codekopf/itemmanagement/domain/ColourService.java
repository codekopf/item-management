package com.codekopf.itemmanagement.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ColourService {
        List<Colour> getAllColours();
        Optional<Colour> getColourById(UUID id);
        Colour saveColour(Colour colour);
        void deleteColour(UUID id);

}
