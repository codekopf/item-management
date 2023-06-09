package com.codekopf.itemmanagement.interfaces.controller;

import com.codekopf.itemmanagement.domain.model.Item;
import com.codekopf.itemmanagement.domain.service.CategoryService;
import com.codekopf.itemmanagement.domain.service.ColourService;
import com.codekopf.itemmanagement.domain.service.ItemService;
import com.codekopf.itemmanagement.interfaces.dto.IncomingItemDTO;
import com.codekopf.itemmanagement.interfaces.dto.OutgoingItemDTO;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@OpenAPIDefinition(info = @Info(title = "Item Management API", version = "1.0", description = "Item Management description")) // TODO - is it in correct place?
@RestController
@RequestMapping("/api/v1/items")
//@Api(value = "Item Management System", tags = "Item") // TODO - is it in correct place?
public final class ItemController {

    private final ItemService itemService;
    private final ColourService colourService;
    private final CategoryService categoryService;

    @Autowired
    public ItemController(ItemService itemService, ColourService colourService, CategoryService categoryService) {
        this.itemService = itemService;
        this.colourService = colourService;
        this.categoryService = categoryService;
    }

    @Operation(summary = "Get all items")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All items are returned")
    })
    @GetMapping
    public ResponseEntity<List<OutgoingItemDTO>> getAllItems() {
        return ResponseEntity.ok(itemService.getAllItems().stream().map(OutgoingItemDTO::of).collect(Collectors.toList()));
    }

    @Operation(summary = "Get item by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item with id is returned"),
            @ApiResponse(responseCode = "404", description = "Item with id does not exist")
    })
    @GetMapping("/{id}")
    public ResponseEntity<OutgoingItemDTO> getItemById(@PathVariable UUID id) {
        log.info("Get item by id {}", id);
        return itemService
                .getItemById(id)
                .map(item -> {
                    log.info("Item found");
                    return ResponseEntity.ok(OutgoingItemDTO.of(item));
                })
                .orElseGet(() -> {
                    log.info("Item with id {} not found", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @Operation(summary = "Creates new item")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "New item is created")
            // TODO add response catching exception
    })
    @RequestMapping(
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE) // TODO This is different than the rest of the code. Unify!
    public ResponseEntity<OutgoingItemDTO> createItem(@RequestBody IncomingItemDTO incomingItemDTO) {

        // TODO Create better validation
        val colour = colourService.getColourById(incomingItemDTO.colourId());
        if (colour.isEmpty()) {
            val message = "Colour with id " + incomingItemDTO.colourId() + " does not exist!";
            log.error(message);
            throw new RuntimeException(message);
        }
        val category = categoryService.getCategoryById(incomingItemDTO.categoryId());
        if (category.isEmpty()) {
            val message = "Category with id " + incomingItemDTO.categoryId() + " does not exist!";
            log.error(message);
            throw new RuntimeException(message);
        }

        val newItem = Item.of( // TODO replace by method of two arguments - question lies where to place it based on its responsibility
                null,
                incomingItemDTO.name(),
                incomingItemDTO.description(),
                incomingItemDTO.price(),
                colour.get(),
                category.get()
        );
        log.info("Create new item: {}", newItem);
        val savedItem = itemService.saveItem(newItem);
        log.info("Saved item: {}", savedItem);
        val savedItemDTO = OutgoingItemDTO.of(savedItem); // TODO What if thrown error
        return ResponseEntity.status(HttpStatus.CREATED).body(savedItemDTO);
    }

    @Operation(summary = "Update item by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Item is updated"),
            // TODO add response catching exception
            @ApiResponse(responseCode = "404", description = "Item with id does not exist")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<OutgoingItemDTO> updateItem(@PathVariable UUID id, @RequestBody IncomingItemDTO incomingItemDTO) {

        // TODO Create better validation
        val colour = colourService.getColourById(incomingItemDTO.colourId());
        if (colour.isEmpty()) {
            val message = "Colour with id " + incomingItemDTO.colourId() + " does not exist!";
            log.error(message);
            throw new RuntimeException(message);
        }
        val category = categoryService.getCategoryById(incomingItemDTO.categoryId());
        if (category.isEmpty()) {
            val message = "Category with id " + incomingItemDTO.categoryId() + " does not exist!";
            log.error(message);
            throw new RuntimeException(message);
        }

        log.info("Update item with id {}", id);
        val item = itemService.getItemById(id);
        if (item.isPresent()) {
            val updatedItem = Item.of( // TODO replace by method of two arguments - question lies where to place it based on its responsibility
                    item.get().id(),
                    incomingItemDTO.name(),
                    incomingItemDTO.description(),
                    incomingItemDTO.price(),
                    colour.get(),
                    category.get()
            );
            log.info("Update: {}", updatedItem);
            val savedItem = itemService.saveItem(updatedItem);
            log.info("Saved item: {}", savedItem);
            val savedItemDTO = OutgoingItemDTO.of(savedItem); // TODO What if thrown error
            return ResponseEntity.ok(savedItemDTO);
        }
        log.info("Item with id {} does not exist", id);
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Delete item by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Item is deleted"),
            @ApiResponse(responseCode = "404", description = "Item with id does not exist")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable UUID id) {
        log.info("Delete item by id {}", id);
        val item = itemService.getItemById(id);
        if (item.isPresent()) {
            itemService.deleteItem(id);
            log.info("Item with id {} is deleted", id);
            return ResponseEntity.noContent().build();
        }
        log.info("Item with id {} does not exist", id);
        return ResponseEntity.notFound().build();
    }

}
