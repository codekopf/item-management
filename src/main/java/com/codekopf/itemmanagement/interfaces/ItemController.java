package com.codekopf.itemmanagement.interfaces;

import com.codekopf.itemmanagement.domain.Item;
import com.codekopf.itemmanagement.domain.ItemService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        var savedItem = itemService.saveItem(newItem);
        var savedItemDTO = OutgoingItemDTO.of(savedItem); // TODO What if thrown error
        return ResponseEntity.status(HttpStatus.CREATED).body(savedItemDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<OutgoingItemDTO> updateItem( @PathVariable UUID id, @RequestBody IncomingItemDTO incomingItemDTO) {
        // TODO incomingItemDTO still need validation - e.g. id of category and colour must exist and can be forged
        var item = itemService.getItemById(id);
        if (item.isPresent()) {
            val updatedItem = Item.of( // TODO replace by method of two arguments - question lies where to place it based on its responsibility
                    item.get().id(),
                    incomingItemDTO.name(),
                    incomingItemDTO.description(),
                    incomingItemDTO.price(),
                    ColourDTO.convertToDomainObject(incomingItemDTO.colourDTO()),
                    CategoryDTO.convertToDomainObject(incomingItemDTO.categoryDTO())
            );
            var savedItem = itemService.saveItem(updatedItem);
            var savedItemDTO = OutgoingItemDTO.of(savedItem); // TODO What if thrown error
            return ResponseEntity.ok(savedItemDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable UUID id) {
        var item = itemService.getItemById(id);
        if (item.isPresent()) {
            itemService.deleteItem(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
