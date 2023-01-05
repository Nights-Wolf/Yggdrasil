package com.yggdrasil.service;

import com.yggdrasil.databaseInterface.CategoryDatabase;
import com.yggdrasil.databaseInterface.ItemDatabase;
import com.yggdrasil.model.Category;
import com.yggdrasil.model.Item;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ItemServiceTest {

    @Mock
    private ItemDatabase itemDatabase;

    @Mock
    private CategoryDatabase categoryDatabase;

    @Mock
    private ItemService itemService;

    private final Category category = new Category(1L, "bursztynowe");
    private final Item item = new Item(1L, "Drzewko", "wadawd",
            new Date(), category, 122, "dawda", 1);

    @BeforeEach
    void setUp() {
        itemService = new ItemService(itemDatabase, categoryDatabase);
    }

    @AfterEach
    void tearDown() {
        categoryDatabase.deleteAll();
        itemDatabase.deleteAll();
    }

    @Test
    void getItem() {
        Mockito.when(itemDatabase.findById(item.getId())).thenReturn(Optional.of(item));

        Item response = itemService.getItem(item.getId());

        Assertions.assertEquals(response, item);
    }

    @Test
    void getItemsByCartItemId() {
        Mockito.when(itemDatabase.findById(item.getId())).thenReturn(Optional.of(item));

        ResponseEntity<Item> response = itemService.getItemsByCartItemId(item.getId());
        Item responseItem = response.getBody();

        Assertions.assertEquals(responseItem, item);
    }

    @Test
    void getAllItems() {
        List<Item> allItems = new ArrayList<>();
        allItems.add(item);

        Mockito.when(itemDatabase.findAll()).thenReturn(allItems);

        List<Item> response = itemService.getAllItems();

        Assertions.assertEquals(response, allItems);
    }

    @Test
    void getItemsByCategory() {
        List<Item> itemList = new ArrayList<>();
        itemList.add(item);
        Mockito.when(categoryDatabase.findById(category.getId())).thenReturn(Optional.of(category));
        Mockito.when(itemDatabase.findByCategoryId(category)).thenReturn(itemList);

        ResponseEntity<List<Item>> response = itemService.getItemsByCategory(category.getId());
        List<Item> responseList = response.getBody();

        Assertions.assertEquals(responseList, itemList);
    }

    @Test
    void addItem() {
        itemService.addItem(item);

        ArgumentCaptor<Item> itemArgumentCaptor = ArgumentCaptor.forClass(Item.class);
        Mockito.verify(itemDatabase).save(itemArgumentCaptor.capture());

        Item capturedItem = itemArgumentCaptor.getValue();

        Assertions.assertEquals(capturedItem, item);
    }

    @Test
    void editItem() {
        Item itemEdited = new Item(1L, "Inne drzewko", "wadawd",
                new Date(), category, 150, "dawda", 1);

        Mockito.when(itemDatabase.findById(item.getId())).thenReturn(Optional.of(item));

        itemService.editItem(item.getId(), itemEdited);

        ArgumentCaptor<Item> itemArgumentCaptor = ArgumentCaptor.forClass(Item.class);
        Mockito.verify(itemDatabase).save(itemArgumentCaptor.capture());

        Item capturedItem = itemArgumentCaptor.getValue();

        Assertions.assertNotEquals(capturedItem.getItemName(), "Drzewko");
    }

    @Test
    void deleteItem() {
        Mockito.when(itemDatabase.save(item)).thenReturn(item);

        itemService.addItem(item);

        itemService.deleteItem(item.getId());

        Mockito.verify(itemDatabase).deleteById(item.getId());
    }
}