package com.yggdrasil.service;

import com.yggdrasil.DAO.FakeUserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private FakeUserDAO fakeUserDAO;

    public void createUser(Long id, String username) {
        fakeUserDAO.createUser(id, username);
    }

    public void getUser(Long id) {
        fakeUserDAO.getUser(id);
    }

    public void editUser(Long id, String username) {
        fakeUserDAO.editUser(id, username);
    }

    public void deleteUser(Long id) {
        fakeUserDAO.deleteUser(id);
    }
}
