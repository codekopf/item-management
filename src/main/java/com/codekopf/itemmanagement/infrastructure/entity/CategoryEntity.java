package com.codekopf.itemmanagement.infrastructure.entity;

import com.codekopf.itemmanagement.configuration.UsedByJpa;
import com.codekopf.itemmanagement.domain.model.Category;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "categories")
public final class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    @UsedByJpa  // TODO not sure about this being public, should be private
    public CategoryEntity() {
    }

    public CategoryEntity(final Category category) {
        this.id = category.id();
        this.name = category.name();
    }

    public Category convertToDomainObject() {
        return Category.of(id, name);
    }

}
