package com.codekopf.itemmanagement.infrastructure;

import com.codekopf.itemmanagement.configuration.UsedByJpa;
import com.codekopf.itemmanagement.domain.Colour;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "colours") // TODO - fix DB
public final class ColourEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    @UsedByJpa  // TODO not sure about this being public, should be private
    public ColourEntity() {
    }

    public ColourEntity(final Colour colour) {
        this.id = colour.getId();
        this.name = colour.getName();
    }

    public Colour convertToDomainObject() {
        return Colour.of(id, name);
    }
}
