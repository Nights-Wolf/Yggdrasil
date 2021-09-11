package com.yggdrasil.api;

import com.yggdrasil.model.User;
import com.yggdrasil.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api/user")
public class UserRestController {

    @Autowired
    private UserService userService;

    @PostMapping
     private void createUser(@RequestBody User user) {
        userService.createUser(user.getId(), user.getUsername());
    }

    @GetMapping("{id}")
    private void getUser(@PathVariable("id") Long id) {
        userService.getUser(id);
    }

    @PutMapping("{id}")
    private void editUser(@PathVariable("id") Long id, @RequestBody User user) {
        userService.editUser(id, user.getUsername());
    }

    @DeleteMapping("{id}")
    private void deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }
}
