package com.codekopf.itemmanagement.interfaces.controller;

import com.codekopf.itemmanagement.domain.model.Colour;
import com.codekopf.itemmanagement.domain.service.ColourService;
import com.codekopf.itemmanagement.interfaces.dto.ColourDTO;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/colours")
public class ColourController {

    private final ColourService colourService;

    @Autowired
    public ColourController(ColourService colourService) {
        this.colourService = colourService;
    }

    @GetMapping
    public ResponseEntity<List<ColourDTO>> getAllColours() {
        return ResponseEntity.ok(colourService.getAllColours().stream().map(ColourDTO::of).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ColourDTO> getColourById(@PathVariable UUID id) {
        return colourService
                .getColourById(id)
                .map(colour -> ResponseEntity.ok(ColourDTO.of(colour)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ColourDTO> createColour(@RequestParam String name) {
        // TODO incomingItemDTO still need validation - e.g. id of category and colourDTO must exist and can be forged

        val newColour = Colour.of(null, name);
        val savedColour = colourService.saveColour(newColour);
        val savedColourDTO = ColourDTO.of(savedColour); // TODO What if thrown error
        return ResponseEntity.status(HttpStatus.CREATED).body(savedColourDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ColourDTO> updateColour(@PathVariable UUID id, @RequestParam String name) {
        // TODO incomingItemDTO still need validation - e.g. id of category and colourDTO must exist and can be forged
        val colour = colourService.getColourById(id);
        if (colour.isPresent()) {
            // TODO replace by method of two arguments - question lies where to place it based on its responsibility
            val updatedColour = Colour.of(colour.get().id(), name); // TODO sanitize user input?
            val saveColour = colourService.saveColour(updatedColour);
            val savedColourDTO = ColourDTO.of(saveColour); // TODO What if thrown error
            return ResponseEntity.ok(savedColourDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteColour(@PathVariable UUID id) {
        val colour = colourService.getColourById(id);
        if (colour.isPresent()) {
            colourService.deleteColour(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
