package com.codekopf.itemmanagement.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

// TODO abuday - @Records
@Getter
@AllArgsConstructor
public final class Item {

    private UUID id;

    private String name;

    private String description;

    private BigDecimal price;

    private Colour colour;

    private Category category;

    public static Item of(final UUID id, final String name, final String description, final BigDecimal price, final Colour colour, final Category category) {
        return new Item(id, name, description, price, colour, category);
    }

}
