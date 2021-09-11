package com.yggdrasil.DAO;

import com.yggdrasil.databaseInterface.UserDatabase;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class FakeUserDAO implements UserDatabase{

    private final HashMap<Long, String> fakeUserRepository = new HashMap<>();

    @Override
    public void createUser(Long id, String username) {
        fakeUserRepository.put(id, username);
        System.out.println(fakeUserRepository.get(1L));
    }

    @Override
    public void getUser(Long id) {
        System.out.println(fakeUserRepository.get(id));
    }

    @Override
    public void editUser(Long id, String username) {
        fakeUserRepository.replace(id, username);
    }

    @Override
    public void deleteUser(Long id) {
        fakeUserRepository.remove(id);
        System.out.println(fakeUserRepository.get(1L));
    }
}
