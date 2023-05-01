package com.codekopf.itemmanagement.domain.service;

import com.codekopf.itemmanagement.domain.model.Item;
import com.codekopf.itemmanagement.infrastructure.entity.ItemEntity;
import com.codekopf.itemmanagement.infrastructure.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public final class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemServiceImpl(final ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public List<Item> getAllItems() {
        return itemRepository.findAll().stream().map(ItemEntity::convertToDomainObject).toList();
    }

    @Override
    public Optional<Item> getItemById(final UUID id) {
        return itemRepository.findById(id).map(ItemEntity::convertToDomainObject);
    }

    // TODO add description - performs save and merge(update) if (id will work)
    @Override
    public Item saveItem(final Item item) {
        return itemRepository.save(new ItemEntity(item)).convertToDomainObject();
    }

    @Override
    public void deleteItem(final UUID id) {
        itemRepository.deleteById(id);
    }

}
