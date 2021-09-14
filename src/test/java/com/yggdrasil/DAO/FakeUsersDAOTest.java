package com.yggdrasil.DAO;

import com.yggdrasil.model.Users;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

class FakeUsersDAOTest {

    @Test
    void createUser() {
        Users users = new Users(1L, "Dawid", "123");
        HashMap<Long, String> map = new HashMap<Long, String>();
        map.put(users.getId(), users.getUsername());
        System.out.println(map.get(1L));
    }
}