package com.codekopf.itemmanagement.interfaces;

import com.codekopf.itemmanagement.domain.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@AllArgsConstructor
public final class OutgoingItemDTO {

    private UUID id;

    private String name;

    private String description;

    private BigDecimal price;

    private ColourDTO colour;

    private CategoryDTO category;

    public static OutgoingItemDTO of(Item item) {
        return new OutgoingItemDTO(
                item.getId(),
                item.getName(),
                item.getDescription(),
                item.getPrice(),
                ColourDTO.of(item.getColour()),
                CategoryDTO.of(item.getCategory())
        );
    }

}
