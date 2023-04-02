package com.codekopf.itemmanagement.interfaces.controller;

import com.codekopf.itemmanagement.domain.model.Category;
import com.codekopf.itemmanagement.domain.service.CategoryService;
import com.codekopf.itemmanagement.interfaces.dto.CategoryDTO;
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
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories().stream().map(CategoryDTO::of).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable UUID id) {
        return categoryService
                .getCategoryById(id)
                .map(category -> ResponseEntity.ok(CategoryDTO.of(category)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@RequestParam String name) {
        // TODO incomingItemDTO still need validation - e.g. id of category and colourDTO must exist and can be forged
        val newCategory = Category.of(UUID.randomUUID(), name); // TODO sanitize user input? TRIM
        val savedCategory = categoryService.saveCategory(newCategory);
        val savedCategoryDTO = CategoryDTO.of(savedCategory); // TODO What if thrown error
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategoryDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable UUID id, @RequestParam String name) {
        // TODO incomingItemDTO still need validation - e.g. id of category and colourDTO must exist and can be forged
        val category = categoryService.getCategoryById(id);
        if (category.isPresent()) {
            // TODO replace by method of two arguments - question lies where to place it based on its responsibility
            val updatedCategory = Category.of(category.get().id(), name); // TODO sanitize user input?
            val saveCategory = categoryService.saveCategory(updatedCategory);
            val savedCategoryDTO = CategoryDTO.of(saveCategory); // TODO What if thrown error
            return ResponseEntity.ok(savedCategoryDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable UUID id) {
        val category = categoryService.getCategoryById(id);
        if (category.isPresent()) {
            categoryService.deleteCategory(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
