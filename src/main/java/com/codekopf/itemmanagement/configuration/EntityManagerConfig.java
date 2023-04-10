package com.codekopf.itemmanagement.configuration;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceContextType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class EntityManagerConfig {

    @PersistenceContext(unitName = "primaryEM", type = PersistenceContextType.TRANSACTION)
    private EntityManager entityManager;

    @Bean("primaryEntityManager")
    @Primary
    public EntityManager primaryEntityManager() {
        return this.entityManager;
    }
}
