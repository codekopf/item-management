package com.codekopf.itemmanagement.domain;

import java.math.BigDecimal;
import java.util.UUID;

public record Item(UUID id, String name, String description, BigDecimal price, Colour colour, Category category) {

    public static Item of(final UUID id, final String name, final String description, final BigDecimal price, final Colour colour, final Category category) {
        return new Item(id, name, description, price, colour, category);
    }

}
