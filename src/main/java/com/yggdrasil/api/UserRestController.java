package com.yggdrasil.api;

import com.yggdrasil.model.ChangeEmail;
import com.yggdrasil.model.ChangePassword;
import com.yggdrasil.model.Users;
import com.yggdrasil.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("api/user")
@CrossOrigin(origins = "*")
public class UserRestController {


    private final UserService userService;


    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
     private void createUser(@RequestBody Users users) {
        userService.createUser(users);
    }

    @GetMapping("/getByToken")
    private ResponseEntity<Users> getUser(HttpServletRequest request, HttpServletResponse response) {
       return userService.getUser(request, response);
    }

    @GetMapping("/getByEmail/{email}")
    private void getUserByEmail(@PathVariable("email") String email) {
       userService.findByEmail(email);
    }

    @PutMapping("/remindPassword/{email}")
    private void remindPassword(@PathVariable("email") String email, @RequestBody Users users) {
        userService.remindPassword(email, users);
    }

    @PutMapping("/changePassword")
    private ResponseEntity changePassword(@RequestBody ChangePassword changePassword) {
        return userService.changePassword(changePassword);
    }

    @PutMapping("/changeEmail")
    private ResponseEntity changeEmail(@RequestBody ChangeEmail changeEmail) {
        return userService.changeEmail(changeEmail);
    }

    @PutMapping("/edit")
    private void editUser(@RequestBody Users users) {
        userService.editUser(users);
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
