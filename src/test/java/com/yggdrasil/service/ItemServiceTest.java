package com.yggdrasil.service;

import com.yggdrasil.model.Item;
import org.junit.jupiter.api.Test;

import java.sql.Time;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ItemServiceTest {

    @Test
    void addItem() {
        Item item = new Item("Tree", new Date(), 25, 2);
        System.out.println();
    }
}