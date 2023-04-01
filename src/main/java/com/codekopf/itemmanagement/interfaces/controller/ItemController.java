package com.codekopf.itemmanagement.interfaces.controller;

import com.codekopf.itemmanagement.domain.model.Item;
import com.codekopf.itemmanagement.domain.service.ItemService;
import com.codekopf.itemmanagement.interfaces.dto.CategoryDTO;
import com.codekopf.itemmanagement.interfaces.dto.ColourDTO;
import com.codekopf.itemmanagement.interfaces.dto.IncomingItemDTO;
import com.codekopf.itemmanagement.interfaces.dto.OutgoingItemDTO;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@OpenAPIDefinition(info = @Info(title = "Item Management API", version = "1.0", description = "Item Management description")) // TODO - is it in correct place?
@RestController
@RequestMapping("/api/v1/items")
//@Api(value = "Item Management System", tags = "Item") // TODO - is it in correct place?
public final class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public ResponseEntity<List<OutgoingItemDTO>> getAllItems() {
        return ResponseEntity.ok(itemService.getAllItems().stream().map(OutgoingItemDTO::of).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OutgoingItemDTO> getItemById(@PathVariable UUID id) {
        return itemService
                .getItemById(id)
                .map(item -> ResponseEntity.ok(OutgoingItemDTO.of(item)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<OutgoingItemDTO> createItem(@RequestBody IncomingItemDTO incomingItemDTO) {
        // TODO incomingItemDTO still need validation - e.g. id of category and colour must exist and can be forged
        val newItem = Item.of( // TODO replace by method of two arguments - question lies where to place it based on its responsibility
                UUID.randomUUID(),
                incomingItemDTO.name(),
                incomingItemDTO.description(),
                incomingItemDTO.price(),
                ColourDTO.convertToDomainObject(incomingItemDTO.colourDTO()),
                CategoryDTO.convertToDomainObject(incomingItemDTO.categoryDTO())
        );
        val savedItem = itemService.saveItem(newItem);
        val savedItemDTO = OutgoingItemDTO.of(savedItem); // TODO What if thrown error
        return ResponseEntity.status(HttpStatus.CREATED).body(savedItemDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<OutgoingItemDTO> updateItem(@PathVariable UUID id, @RequestBody IncomingItemDTO incomingItemDTO) {
        // TODO incomingItemDTO still need validation - e.g. id of category and colour must exist and can be forged
        val item = itemService.getItemById(id);
        if (item.isPresent()) {
            val updatedItem = Item.of( // TODO replace by method of two arguments - question lies where to place it based on its responsibility
                    item.get().id(),
                    incomingItemDTO.name(),
                    incomingItemDTO.description(),
                    incomingItemDTO.price(),
                    ColourDTO.convertToDomainObject(incomingItemDTO.colourDTO()),
                    CategoryDTO.convertToDomainObject(incomingItemDTO.categoryDTO())
            );
            val savedItem = itemService.saveItem(updatedItem);
            val savedItemDTO = OutgoingItemDTO.of(savedItem); // TODO What if thrown error
            return ResponseEntity.ok(savedItemDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable UUID id) {
        val item = itemService.getItemById(id);
        if (item.isPresent()) {
            itemService.deleteItem(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
