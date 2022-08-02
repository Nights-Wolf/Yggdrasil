package com.yggdrasil.api;

import com.yggdrasil.model.Users;
import com.yggdrasil.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("api/user")
@CrossOrigin(origins = "*")
public class UserRestController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
     private void createUser(@RequestBody Users users) {
        userService.createUser(users);
    }

    @GetMapping("{id}")
    private void getUser(@PathVariable("id") Long id) {
        userService.getUser(id);
    }

    @PutMapping("{id}")
    private void editUser(@PathVariable("id") Long id, @RequestBody Users users) {
        userService.editUser(id, users);
    }

    @DeleteMapping("{id}")
    private void deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }

    @RequestMapping(value = "/promote/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    private void grantAdmin(@PathVariable("id") Long id) {
        userService.grantAdmin(id);
    }
}
