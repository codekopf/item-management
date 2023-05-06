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

@Slf4j
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
        log.info("Get category by id: {}", id);
        return categoryService
                .getCategoryById(id)
                .map(category -> ResponseEntity.ok(CategoryDTO.of(category)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@Nonnull @RequestParam(required = true) String name) {
        // TODO name still need validation - e.g. id of category and categoryDTO must exist and can be forged
        log.info("Create new category: {}", name);
        val newCategory = Category.of(null, name); // TODO sanitize user input? TRIM
        val savedCategory = categoryService.saveCategory(newCategory);
        log.info("Saved category: {}", savedCategory);
        val savedCategoryDTO = CategoryDTO.of(savedCategory); // TODO What if thrown error
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategoryDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable UUID id, @Nonnull @RequestParam(required = true) String name) {
        // TODO name still need validation - e.g. id of category and categoryDTO must exist and can be forged
        log.info("Update category with id: {}", id);
        val category = categoryService.getCategoryById(id);
        if (category.isPresent()) {
            // TODO replace by method of two arguments - question lies where to place it based on its responsibility
            val updatedCategory = Category.of(category.get().id(), name); // TODO sanitize user input?
            log.info("Update: {}", updatedCategory);
            val saveCategory = categoryService.saveCategory(updatedCategory);
            log.info("Saved category: {}", saveCategory);
            val savedCategoryDTO = CategoryDTO.of(saveCategory); // TODO What if thrown error
            return ResponseEntity.ok(savedCategoryDTO);
        }
        log.info("Category with id: {} does not exist", id);
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable UUID id) {
        log.info("Delete category by id: {}", id);
        val category = categoryService.getCategoryById(id);
        if (category.isPresent()) {
            categoryService.deleteCategory(id);
            log.info("Category with id: {} is deleted", id);
            return ResponseEntity.noContent().build();
        }
        log.info("Category with id: {} does not exist", id);
        return ResponseEntity.notFound().build();
    }

}
