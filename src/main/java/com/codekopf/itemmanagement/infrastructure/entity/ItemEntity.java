package com.codekopf.itemmanagement.infrastructure.entity;

import com.codekopf.itemmanagement.configuration.UsedByJpa;
import com.codekopf.itemmanagement.domain.model.Item;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "items")
@EqualsAndHashCode(of = "id")
@Getter
@AllArgsConstructor
public final class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    private String description;

    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "ItemColours")
    private ColourEntity colour;

    @ManyToOne
    @JoinColumn(name = "ItemCategories")
    private CategoryEntity category;

    @UsedByJpa // TODO not sure about this being public, should be private
    public ItemEntity() {
    }

    public ItemEntity(final Item item) {
        this.id = item.id();
        this.name = item.name();
        this.description = item.description();
        this.price = item.price();
        this.colour = new ColourEntity(item.colour());
        this.category = new CategoryEntity(item.category());
    }

    public Item convertToDomainObject() {
        return Item.of(id, name, description, price, colour.convertToDomainObject(), category.convertToDomainObject());
    }

}
