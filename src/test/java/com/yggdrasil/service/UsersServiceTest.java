package com.yggdrasil.service;

import com.yggdrasil.model.Users;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UsersServiceTest {

    @Test
    void createUser() {
        Users users = new Users(1L, "Dawid", "123");
        assertEquals( 1L, users.getId(), "User ID is correct");
        assertEquals("Dawid", users.getUsername(), "username Dawid is correct");
    }
}