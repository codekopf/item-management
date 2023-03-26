package com.codekopf.itemmanagement.interfaces;

import com.codekopf.itemmanagement.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public final class CategoryDTO {

    private UUID id;

    private String name;

    public static CategoryDTO of(final Category category) {
        return new CategoryDTO(category.getId(), category.getName());
    }

    public static Category convertToDomainObject(final CategoryDTO categoryDTO) {
        return new Category(categoryDTO.getId(), categoryDTO.getName());
    }

}
