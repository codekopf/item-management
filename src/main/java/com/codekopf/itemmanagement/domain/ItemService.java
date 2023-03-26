package com.codekopf.itemmanagement.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ItemService {
        List<Item> getAllItems();
        Optional<Item> getItemById(UUID id);
        Item saveItem(Item item);
        void deleteItem(UUID id);
}
