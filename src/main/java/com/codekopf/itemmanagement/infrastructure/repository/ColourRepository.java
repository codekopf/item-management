package com.codekopf.itemmanagement.infrastructure.repository;

import com.codekopf.itemmanagement.infrastructure.entity.ColourEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ColourRepository extends JpaRepository<ColourEntity, UUID> {
}
