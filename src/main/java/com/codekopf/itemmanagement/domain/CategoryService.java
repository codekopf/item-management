package com.codekopf.itemmanagement.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryService {
        List<Category> getAllCategories();
        Optional<Category> getCategoryById(UUID id);
        Category saveCategory(Category category);
        void deleteCategory(UUID id);

}
