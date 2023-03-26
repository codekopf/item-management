package com.codekopf.itemmanagement.interfaces;

import com.codekopf.itemmanagement.domain.Item;
import com.codekopf.itemmanagement.domain.ItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/items")
@Api(value = "Item Management System", tags = "Item")
public final class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping
    @ApiOperation(value = "View a list of all items", response = List.class)
    public ResponseEntity<List<OutgoingItemDTO>> getAllItems() {
        return ResponseEntity.ok(itemService.getAllItems().stream().map(OutgoingItemDTO::of).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get an item by ID", response = OutgoingItemDTO.class)
    public ResponseEntity<OutgoingItemDTO> getItemById(@ApiParam(value = "Item ID to retrieve", required = true) @PathVariable UUID id) {
        return itemService
                .getItemById(id)
                .map(item -> ResponseEntity.ok(OutgoingItemDTO.of(item)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ApiOperation(value = "Create a new item", response = OutgoingItemDTO.class)
    public ResponseEntity<OutgoingItemDTO> createItem(@ApiParam(value = "Item object to store", required = true) @RequestBody IncomingItemDTO incomingItemDTO) {
        // TODO incomingItemDTO still need validation - e.g. id of category and colour must exist and can be forged
        val newItem = Item.of( // TODO replace by method of two arguments - question lies where to place it based on its responsibility
                UUID.randomUUID(),
                incomingItemDTO.getName(),
                incomingItemDTO.getDescription(),
                incomingItemDTO.getPrice(),
                ColourDTO.convertToDomainObject(incomingItemDTO.getColourDTO()),
                CategoryDTO.convertToDomainObject(incomingItemDTO.getCategoryDTO())
        );
        var savedItem = itemService.saveItem(newItem);
        var savedItemDTO = OutgoingItemDTO.of(savedItem); // TODO What if thrown error
        return ResponseEntity.status(HttpStatus.CREATED).body(savedItemDTO);
    }

    @PatchMapping("/{id}")
    @ApiOperation(value = "Update an existing item", response = OutgoingItemDTO.class)
    public ResponseEntity<OutgoingItemDTO> updateItem(@ApiParam(value = "Item ID to update", required = true) @PathVariable UUID id, @ApiParam(value = "Updated item object", required = true) @RequestBody IncomingItemDTO incomingItemDTO) {
        // TODO incomingItemDTO still need validation - e.g. id of category and colour must exist and can be forged
        var item = itemService.getItemById(id);
        if (item.isPresent()) {
            val updatedItem = Item.of( // TODO replace by method of two arguments - question lies where to place it based on its responsibility
                    item.get().getId(),
                    incomingItemDTO.getName(),
                    incomingItemDTO.getDescription(),
                    incomingItemDTO.getPrice(),
                    ColourDTO.convertToDomainObject(incomingItemDTO.getColourDTO()),
                    CategoryDTO.convertToDomainObject(incomingItemDTO.getCategoryDTO())
            );
            var savedItem = itemService.saveItem(updatedItem);
            var savedItemDTO = OutgoingItemDTO.of(savedItem); // TODO What if thrown error
            return ResponseEntity.ok(savedItemDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete an item by ID")
    public ResponseEntity<Void> deleteItem(@ApiParam(value = "Item ID to delete", required = true) @PathVariable UUID id) {
        var item = itemService.getItemById(id);
        if (item.isPresent()) {
            itemService.deleteItem(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
