package com.yggdrasil.service;

import com.yggdrasil.DAO.ItemDAO;
import com.yggdrasil.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    @Autowired
    private ItemDAO itemDAO;

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
