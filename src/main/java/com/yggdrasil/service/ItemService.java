package com.yggdrasil.service;

import com.yggdrasil.databaseInterface.CategoryDatabase;
import com.yggdrasil.databaseInterface.ItemDatabase;
import com.yggdrasil.model.Category;
import com.yggdrasil.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private final ItemDatabase itemDatabase;
    private final CategoryDatabase categoryDatabase;

    @Autowired
    public ItemService(ItemDatabase itemDatabase, CategoryDatabase categoryDatabase) {
        this.itemDatabase = itemDatabase;
        this.categoryDatabase = categoryDatabase;
    }

    public Item getItem(Long id) {
        return itemDatabase.findById(id).orElseThrow();
    }

    public ResponseEntity<Item> getItemsByCartItemId(Long id) {
        Item item = itemDatabase.findById(id).orElseThrow();

        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    public List<Item> getAllItems() {
        return itemDatabase.findAll();
    }

    public ResponseEntity<List<Item>> getItemsByCategory(Long categoryId) {
        Category category = categoryDatabase.findById(categoryId).orElseThrow();
        List<Item> itemList = itemDatabase.findByCategoryId(category);
        return new ResponseEntity<>(itemList, HttpStatus.OK);
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
