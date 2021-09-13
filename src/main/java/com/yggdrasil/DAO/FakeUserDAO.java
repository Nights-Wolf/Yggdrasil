package com.yggdrasil.DAO;

import com.yggdrasil.databaseInterface.UserDatabase;
import com.yggdrasil.model.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class FakeUserDAO implements UserDatabase{

    private final HashMap<Long, User> fakeUserRepository = new HashMap<>();

    @Override
    public void createUser(User user) {
        fakeUserRepository.put(user.getId(), user);
        System.out.println(fakeUserRepository.get(1L));
    }

    @Override
    public void getUser(Long id) {
        System.out.println(fakeUserRepository.get(id));
    }

    @Override
    public void editUser(Long id, User user) {
        fakeUserRepository.replace(id, user);
    }

    @Override
    public void deleteUser(Long id) {
        fakeUserRepository.remove(id);
        System.out.println(fakeUserRepository.get(1L));
    }
}
