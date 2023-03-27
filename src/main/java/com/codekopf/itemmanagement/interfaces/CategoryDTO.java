package com.codekopf.itemmanagement.interfaces;

import com.codekopf.itemmanagement.domain.Category;

import java.util.UUID;

public record CategoryDTO(UUID id, String name) {

    public static CategoryDTO of(final Category category) {
        return new CategoryDTO(category.id(), category.name());
    }

    public static Category convertToDomainObject(final CategoryDTO categoryDTO) {
        return new Category(categoryDTO.id(), categoryDTO.name());
    }

}
