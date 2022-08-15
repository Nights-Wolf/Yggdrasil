package com.yggdrasil.service;

import com.yggdrasil.DAO.ItemDAO;
import com.yggdrasil.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemDAO itemDAO;

    public Item getItem(Long id) {
        return itemDAO.getItem(id);
    }

    public List<Item> getAllItems() {
        return itemDAO.getAllItems();
    }

    public List<Item> getItemsByCategory(Long categoryId) {
        return itemDAO.getItemsByCategory(categoryId);
    }
    public void addItem(Item item) {
        itemDAO.addItem(item);
    }

    public void editItem(Long id, Item item) {
        itemDAO.editItem(id, item);
    }

    public void deleteItem(Long id) {
        itemDAO.deleteItem(id);
    }
}
