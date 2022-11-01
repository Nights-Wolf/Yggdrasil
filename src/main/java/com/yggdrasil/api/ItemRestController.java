package com.yggdrasil.api;

import com.yggdrasil.model.Item;
import com.yggdrasil.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/item")
@CrossOrigin(origins = "*")
public class ItemRestController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/{id}")
    private ResponseEntity<Item> getItem(@PathVariable("id") Long id) {
        Item item = itemService.getItem(id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    };

    @GetMapping("/all/{id}")
    private Item getItemsById(@PathVariable("id") Long id) {
        return itemService.getItemsById(id);
    }

    @GetMapping("/all")
    private List<Item> getAllItems() {
        return itemService.getAllItems();
    }

    @GetMapping("/category/{categoryId}")
    private List<Item> getItemsByCategory(@PathVariable Long categoryId) {
        return itemService.getItemsByCategory(categoryId);
    }

    @PostMapping
    private void addItem(@RequestBody Item item) {
        itemService.addItem(item);
    }

    @PutMapping("{id}")
    private void editItem(@PathVariable("id") Long id, @RequestBody Item item) {
        itemService.editItem(id, item);
    }

    @DeleteMapping("{id}")
    private void deleteItem(@PathVariable("id") Long id) {
        itemService.deleteItem(id);
    }
}
