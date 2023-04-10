package com.codekopf.itemmanagement.infrastructure.repository;

import com.codekopf.itemmanagement.infrastructure.entity.CategoryEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Transactional
public class CategoryRepositoryImpl implements CategoryRepository {

    private final JpaRepositoryCategory jpaRepositoryCategory;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public CategoryRepositoryImpl(final EntityManager entityManager,
                                  final JpaRepositoryCategory jpaRepositoryCategory) {
        this.entityManager = entityManager;
        this.jpaRepositoryCategory = jpaRepositoryCategory;
    }

    @Override
    public void store(final CategoryEntity categoryEntity) {
        if (this.entityManager.find(CategoryEntity.class, categoryEntity.getId()) == null) {
            this.entityManager.persist(categoryEntity);
        } else {
            this.entityManager.merge(categoryEntity);
        }
    }

}
