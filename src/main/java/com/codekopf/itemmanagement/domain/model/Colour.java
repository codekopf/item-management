package com.codekopf.itemmanagement.domain.model;

import java.util.UUID;

public record Colour(UUID id, String name) {

    public static Colour of(final UUID id, final String name) {
        return new Colour(id, name);
    }

}
