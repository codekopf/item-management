package com.codekopf.itemmanagement.domain.model;

import java.util.UUID;

public record Category(UUID id, String name) {

    public static Category of(UUID id, String name) {
        return new Category(id, name);
    }

}
