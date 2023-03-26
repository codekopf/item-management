package com.codekopf.itemmanagement.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public final class Colour {

    private UUID id;

    private String name;

    public static Colour of(final UUID id, final String name) {
        return new Colour(id, name);
    }

}
