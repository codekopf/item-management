package com.codekopf.itemmanagement.infrastructure.entity;

import com.codekopf.itemmanagement.configuration.UsedByJpa;
import com.codekopf.itemmanagement.domain.model.Colour;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "colours")
public final class ColourEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    @UsedByJpa  // TODO not sure about this being public, should be private
    public ColourEntity() {
    }

    public ColourEntity(final Colour colour) {
        this.id = colour.id();
        this.name = colour.name();
    }

    public Colour convertToDomainObject() {
        return Colour.of(id, name);
    }

}
