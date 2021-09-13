package com.yggdrasil.DAO;

import com.yggdrasil.databaseInterface.ItemDatabase;
import com.yggdrasil.model.Item;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class FakeItemDAO implements ItemDatabase {

    HashMap<Long, Item> fakeItemRepository = new HashMap<>();

    @Override
    public void addItem(Item item) {
        fakeItemRepository.put(item.getId(), item);
        System.out.println(fakeItemRepository.get(1L).getItemName());
    }

    @Override
    public void editItem(Long id, Item item) {
        fakeItemRepository.replace(id, item);
        System.out.println(fakeItemRepository.get(1L).getItemName());
    }

    @Override
    public void deleteItem(Long id) {
        fakeItemRepository.remove(id);
        System.out.println(fakeItemRepository.get(1L));
    }
}
