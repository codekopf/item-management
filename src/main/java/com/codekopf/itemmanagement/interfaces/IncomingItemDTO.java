package com.codekopf.itemmanagement.interfaces;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public final class IncomingItemDTO {

    private String name;

    private String description;

    private BigDecimal price;

    private ColourDTO colourDTO; // TODO abuday - I need to carry only colour id here

    private CategoryDTO categoryDTO;  // TODO abuday - I need to carry only category id here

}
