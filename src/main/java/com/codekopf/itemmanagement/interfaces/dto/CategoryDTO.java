package com.codekopf.itemmanagement.interfaces.dto;

import com.codekopf.itemmanagement.domain.model.Category;

import java.util.UUID;

public record CategoryDTO(UUID id, String name) {

    public static CategoryDTO of(final Category category) {
        return new CategoryDTO(category.id(), category.name());
    }

}
