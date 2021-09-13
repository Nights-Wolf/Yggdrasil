package com.yggdrasil.service;

import com.yggdrasil.DAO.FakeUserDAO;
import com.yggdrasil.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private FakeUserDAO fakeUserDAO;

    public void createUser(User user) {
        fakeUserDAO.createUser(user);
    }

    public void getUser(Long id) {
        fakeUserDAO.getUser(id);
    }

    public void editUser(Long id, User user) {
        fakeUserDAO.editUser(id, user);
    }

    public void deleteUser(Long id) {
        fakeUserDAO.deleteUser(id);
    }
}
