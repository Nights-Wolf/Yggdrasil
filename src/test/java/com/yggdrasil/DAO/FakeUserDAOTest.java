package com.yggdrasil.DAO;

import com.yggdrasil.model.User;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class FakeUserDAOTest {

    @Test
    void createUser() {
        User user = new User(1L, "Dawid");
        HashMap<Long, String> map = new HashMap<Long, String>();
        map.put(user.getId(), user.getUsername());
        System.out.println(map.get(1L));
    }
}