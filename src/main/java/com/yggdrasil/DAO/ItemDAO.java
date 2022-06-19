package com.yggdrasil.DAO;

import com.yggdrasil.databaseInterface.ItemDatabase;
import com.yggdrasil.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ItemDAO {

    @Autowired
    private final ItemDatabase itemDatabase;

    public ItemDAO(ItemDatabase itemDatabase) {
        this.itemDatabase = itemDatabase;
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
