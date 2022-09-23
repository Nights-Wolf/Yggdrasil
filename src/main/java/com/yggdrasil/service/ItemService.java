package com.yggdrasil.service;

import com.yggdrasil.databaseInterface.ItemDatabase;
import com.yggdrasil.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private final ItemDatabase itemDatabase;

    @Autowired
    public ItemService(ItemDatabase itemDatabase) {
        this.itemDatabase = itemDatabase;
    }

    public Item getItem(Long id) {
        return itemDatabase.findById(id).orElseThrow();
    }

    public List<Item> getAllItems() {
        return itemDatabase.findAll();
    }

    public List<Item> getItemsByCategory(Long categoryId) {
        return itemDatabase.findByCategoryId(categoryId);
    }
    public void addItem(Item item) {
        itemDatabase.save(item);
    }

    public void editItem(Long id, Item item) {
        Item editedItem = itemDatabase.findById(id).orElseThrow();

        editedItem.setItemName(item.getItemName());
        editedItem.setCreated(item.getCreated());
        editedItem.setCategoryId(item.getCategoryId());
        editedItem.setPrice(item.getPrice());
        editedItem.setItemsLeft(item.getItemsLeft());

        itemDatabase.save(editedItem);
    }

    public void deleteItem(Long id) {
        itemDatabase.deleteById(id);
    }
}
