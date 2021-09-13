package com.yggdrasil.service;

import com.yggdrasil.DAO.FakeItemDAO;
import com.yggdrasil.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    @Autowired
    private FakeItemDAO fakeItemDAO;

    public void addItem(Item item) {
        fakeItemDAO.addItem(item);
    }

    public void editItem(Long id, Item item) {
        fakeItemDAO.editItem(id, item);
    }

    public void deleteItem(Long id) {
        fakeItemDAO.deleteItem(id);
    }
}
