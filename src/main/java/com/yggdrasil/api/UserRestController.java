package com.yggdrasil.api;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yggdrasil.model.Users;
import com.yggdrasil.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("api/user")

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

    @GetMapping("{id}")
    private void getUser(@PathVariable("id") Long id) {
        userService.getUser(id);
    }

    @GetMapping("/getByEmail/{email}")
    private void getUserByEmail(@PathVariable("email") String email) {
       userService.findByEmail(email);
    }

    @GetMapping("/checkPassword/{email}/{passwordToCheck}")
    private HttpStatus getUserPassword(@PathVariable("email") String email, @PathVariable("passwordToCheck") String passwordToCheck) {
        return userService.getUserPassword(email, passwordToCheck);
    }

    @PutMapping("/changePassword/{email}")
    private void changePassword(@PathVariable("email") String email, @RequestBody Users users) {
        userService.changePassword(email, users);
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
