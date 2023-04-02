package com.codekopf.itemmanagement.infrastructure.entity;

import com.codekopf.itemmanagement.configuration.UsedByJpa;
import com.codekopf.itemmanagement.domain.model.Item;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "colour_id")
    private ColourEntity colour;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
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
