package com.codekopf.itemmanagement.infrastructure.repository;

import com.codekopf.itemmanagement.infrastructure.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<CategoryEntity, UUID> {
}
