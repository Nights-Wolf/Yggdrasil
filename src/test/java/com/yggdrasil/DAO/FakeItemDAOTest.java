package com.yggdrasil.DAO;

import com.yggdrasil.model.Item;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;

class FakeItemDAOTest {

    @Test
    void addItem() {
        Item item = new Item(1L, "Tree", new Date(), 25, 2);
        HashMap<Long, Item> map = new HashMap<>();
        map.put(item.getId(), item);
        System.out.println(map.get(1L));
    }

    @Test
    void editItem() {
        Item item = new Item(1L, "Tree", new Date(), 25, 2);
        HashMap<Long, Item> map = new HashMap<>();
        map.put(item.getId(), item);
        Item itemReplace = new Item(1L, "TreeBeard", new Date(), 30, 2);
        map.replace(1L, itemReplace);
        System.out.println(map.get(1L).getItemName());
    }

    @Test
    void deleteItem() {
        Item item = new Item(1L, "Tree", new Date(), 25, 2);
        HashMap<Long, Item> map = new HashMap<>();
        map.put(item.getId(), item);
        map.remove(1L);
        System.out.println(map.get(1L));
    }
}