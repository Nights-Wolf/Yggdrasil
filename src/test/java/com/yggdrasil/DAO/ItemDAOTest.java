package com.yggdrasil.DAO;

import com.yggdrasil.model.Item;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;

class ItemDAOTest {

    @Test
    void addItem() {
        Item item = new Item("Tree", new Date(), 25, 2);
        HashMap<Long, Item> map = new HashMap<>();
        map.put(item.getId(), item);
        System.out.println(map.get(1L));
    }

    @Test
    void editItem() {
        Item item = new Item("Tree", new Date(), 25, 2);
        HashMap<Long, Item> map = new HashMap<>();
        map.put(item.getId(), item);
        Item itemReplace = new Item("TreeBeard", new Date(), 30, 2);
        map.replace(1L, itemReplace);
        System.out.println(map.get(1L).getItemName());
    }

    @Test
    void deleteItem() {
        Item item = new Item("Tree", new Date(), 25, 2);
        HashMap<Long, Item> map = new HashMap<>();
        map.put(item.getId(), item);
        map.remove(1L);
        System.out.println(map.get(1L));
    }
}