package com.codekopf.itemmanagement.interfaces.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record IncomingItemDTO(String name, String description, BigDecimal price, UUID colourId, UUID categoryId) {

}
