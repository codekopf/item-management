package com.codekopf.itemmanagement.interfaces.dto;

import com.codekopf.itemmanagement.domain.model.Item;

import java.math.BigDecimal;
import java.util.UUID;

public record OutgoingItemDTO(UUID id, String name, String description, BigDecimal price, ColourDTO colour, CategoryDTO category) {

    public static OutgoingItemDTO of(final Item item) {
        return new OutgoingItemDTO(
                item.id(),
                item.name(),
                item.description(),
                item.price(),
                ColourDTO.of(item.colour()),
                CategoryDTO.of(item.category())
        );
    }

}
