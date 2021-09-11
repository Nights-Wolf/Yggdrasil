package com.yggdrasil.service;

import com.yggdrasil.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceTest {

    @Test
    void createUser() {
        User user = new User(1L, "Dawid");
        assertEquals( 1L, user.getId(), "User ID is correct");
        assertEquals("Dawid", user.getUsername(), "username Dawid is correct");
    }
}