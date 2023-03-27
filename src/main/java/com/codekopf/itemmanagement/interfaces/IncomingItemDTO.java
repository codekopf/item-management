package com.codekopf.itemmanagement.interfaces;

import java.math.BigDecimal;

/**
 * @param colourDTO   TODO abuday - I need to carry only colour id here
 * @param categoryDTO TODO abuday - I need to carry only category id here
 */
public record IncomingItemDTO(String name, String description, BigDecimal price, ColourDTO colourDTO, CategoryDTO categoryDTO) {

}
