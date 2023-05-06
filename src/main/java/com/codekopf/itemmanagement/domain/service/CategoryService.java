package com.codekopf.itemmanagement.domain.service;

import com.codekopf.itemmanagement.domain.model.Category;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryService {

        /**
         * Get all categories
         *
         * @return List of {@link Category}
         */
        List<Category> getAllCategories();

        /**
         * Get {@link Category} by id
         *
         * @param id {@link UUID} of the category
         * @return Optional of {@link Category}
         */
        Optional<Category> getCategoryById(UUID id);

        // TODO abuday - create separate method for saving and updating
        /**
         * Create a new {@link Category}
         *
         * @param category {@link Category} to save
         * @return Created {@link Category}
         */
        Category saveCategory(Category category);

        /**
         * Delete {@link Category} by id
         *
         * @param id {@link UUID} of the {@link Category}
         */
        void deleteCategory(UUID id);

}
