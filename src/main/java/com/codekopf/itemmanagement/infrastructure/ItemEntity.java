package com.codekopf.itemmanagement.infrastructure;

import com.codekopf.itemmanagement.configuration.UsedByJpa;
import com.codekopf.itemmanagement.domain.Item;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "items") // TODO - fix DB
@EqualsAndHashCode(of = "id")
@Getter
@AllArgsConstructor
public final class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    private String name;

    private String description;

    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "colour_id")
    private ColourEntity colour;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @UsedByJpa // TODO not sure about this being public, should be private
    public ItemEntity() {
    }

    public ItemEntity(final Item item) {
        this.id = item.getId();
        this.name = item.getName();
        this.description = item.getDescription();
        this.price = item.getPrice();
        this.colour = new ColourEntity(item.getColour());
        this.category = new CategoryEntity(item.getCategory());
    }

    public Item convertToDomainObject() {
        return Item.of(id, name, description, price, colour.convertToDomainObject(), category.convertToDomainObject());
    }

}
