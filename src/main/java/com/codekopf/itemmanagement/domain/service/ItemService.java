package com.codekopf.itemmanagement.domain.service;

import com.codekopf.itemmanagement.domain.model.Item;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ItemService {

        /**
         * Get all items
         *
         * @return List of {@link Item}
         */
        List<Item> getAllItems();

        /**
         * Get {@link Item} by id
         *
         * @param id {@link UUID} of the item
         * @return Optional of {@link Item}
         */
        Optional<Item> getItemById(UUID id);

        // TODO abuday - create separate method for saving and updating
        /**
         * Create a new {@link Item}
         *
         * @param item {@link Item} to save
         * @return Created {@link Item}
         */
        Item saveItem(Item item);

        /**
         * Delete {@link Item} by id
         *
         * @param id {@link UUID} of the {@link Item}
         */
        void deleteItem(UUID id);

}
