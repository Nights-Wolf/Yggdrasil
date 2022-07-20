package com.yggdrasil.api;

import com.yggdrasil.model.Item;
import com.yggdrasil.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/item")
public class ItemRestController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/{id}")
    @CrossOrigin(origins = "*")
    private ResponseEntity<Item> getItem(@PathVariable Long id) {
        Item item = itemService.getItem(id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    };

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
