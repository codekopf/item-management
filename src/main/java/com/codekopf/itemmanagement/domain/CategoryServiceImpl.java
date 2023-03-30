package com.codekopf.itemmanagement.domain;

import com.codekopf.itemmanagement.infrastructure.CategoryEntity;
import com.codekopf.itemmanagement.infrastructure.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public final class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(final CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll().stream().map(CategoryEntity::convertToDomainObject).toList();
    }

    public Optional<Category> getCategoryById(final UUID id) {
        return categoryRepository.findById(id).map(CategoryEntity::convertToDomainObject);
    }

    // TODO add description - performs save and merge(update) if (id will work)
    public Category saveCategory(final Category category) {
        return categoryRepository.save(new CategoryEntity(category)).convertToDomainObject();
    }

    public void deleteCategory(final UUID id) {
        categoryRepository.deleteById(id);
    }
}
