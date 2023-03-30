package com.codekopf.itemmanagement.infrastructure.repository;

import com.codekopf.itemmanagement.infrastructure.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ItemRepository extends JpaRepository<ItemEntity, UUID> {
}
