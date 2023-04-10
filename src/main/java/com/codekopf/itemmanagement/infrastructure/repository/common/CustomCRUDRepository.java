package com.codekopf.itemmanagement.infrastructure.repository.common;

import com.codekopf.itemmanagement.infrastructure.entity.CategoryEntity;

// Define custom general name for CRUD operations for all entities using CustomCRUDRepository
public interface CustomCRUDRepository<T> extends FilteredAndPaginated<T> {

    void store(final CategoryEntity categoryEntity);

    // TODO soft delete

}
