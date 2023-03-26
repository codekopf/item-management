package com.codekopf.itemmanagement.domain;

import com.codekopf.itemmanagement.infrastructure.ItemEntity;
import com.codekopf.itemmanagement.infrastructure.ItemRepository;
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

    public List<Item> getAllItems() {
        return itemRepository.findAll().stream().map(ItemEntity::convertToDomainObject).toList();
    }

    public Optional<Item> getItemById(final UUID id) {
        return itemRepository.findById(id).map(ItemEntity::convertToDomainObject);
    }

    // TODO add description - performs save and merge(update) if (id will work)
    public Item saveItem(final Item item) {
        return itemRepository.save(new ItemEntity(item)).convertToDomainObject();
    }

    public void deleteItem(final UUID id) {
        itemRepository.deleteById(id);
    }
}
