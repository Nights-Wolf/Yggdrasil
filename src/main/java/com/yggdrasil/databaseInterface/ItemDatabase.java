package com.yggdrasil.databaseInterface;

import com.yggdrasil.model.Item;

public interface ItemDatabase {

    void addItem(Item item);
    void editItem(String itemId, Item item);
    void deleteItem(String itemId, Item item);
}
