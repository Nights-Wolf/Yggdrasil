package com.yggdrasil.service;

import com.yggdrasil.DAO.UserDAO;
import com.yggdrasil.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    public void createUser(Users users) {
        userDAO.createUser(users);
    }

    public void getUser(Long id) {
        userDAO.getUser(id);
    }

    public void editUser(Long id, Users users) {
        userDAO.editUser(id, users);
    }

    public void deleteUser(Long id) {
        userDAO.deleteUser(id);
    }

    public void grantAdmin(Long id) {
        userDAO.grantAdmin(id);
    }
}
