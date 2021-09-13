package com.yggdrasil.databaseInterface;

import com.yggdrasil.model.Item;

public interface ItemDatabase {

    void addItem(Item item);
    void editItem(Long id, Item item);
    void deleteItem(Long id);
}
