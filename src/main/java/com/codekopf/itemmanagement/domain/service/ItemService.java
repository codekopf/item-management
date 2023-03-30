package com.codekopf.itemmanagement.domain.service;

import com.codekopf.itemmanagement.domain.model.Item;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ItemService {
        List<Item> getAllItems();
        Optional<Item> getItemById(UUID id);
        Item saveItem(Item item);
        void deleteItem(UUID id);

}
