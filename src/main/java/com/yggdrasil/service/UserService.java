package com.yggdrasil.service;

import com.yggdrasil.DAO.UserDAO;
import com.yggdrasil.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    public void createUser(Users users) {
        userDAO.createUser(users);
    }

    public Users getUser(Long id) {
       return userDAO.getUser(id);
    }

    public void editUser(Long id, Users users) {
        userDAO.editUser(id, users);
    }

    public void setToken(Long id, Long refreshToken) {
        userDAO.setToken(id, refreshToken);
    }

    public void deleteToken(Long id) {
        userDAO.deleteToken(id);
    }

    public void deleteUser(Long id) {
        userDAO.deleteUser(id);
    }

    public void grantAdmin(Long id) {
        userDAO.grantAdmin(id);
    }

    public Users findByEmail(String email) {
        return userDAO.findByEmail(email);
    }

    public void changePassword(String email, Users users) {
        userDAO.changePassword(email, users);
    }

    public void rememberMeCheck(String email) {
        userDAO.rememberMeCheck(email);
    }
}
