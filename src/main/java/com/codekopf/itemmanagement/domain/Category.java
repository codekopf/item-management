package com.codekopf.itemmanagement.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public final class Category {

    private UUID id;

    private String name;

    public static Category of(UUID id, String name) {
        return new Category(id, name);
    }

}
