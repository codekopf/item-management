package com.codekopf.itemmanagement.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ColourRepository extends JpaRepository<ColourEntity, UUID> {
}
