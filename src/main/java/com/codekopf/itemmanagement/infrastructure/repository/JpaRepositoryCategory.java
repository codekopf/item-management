package com.codekopf.itemmanagement.infrastructure.repository;

import com.codekopf.itemmanagement.infrastructure.entity.CategoryEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface JpaRepositoryCategory extends PagingAndSortingRepository<CategoryEntity, UUID> {

}
