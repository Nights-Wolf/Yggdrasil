package com.yggdrasil.DAO;

import com.yggdrasil.databaseInterface.UserDatabase;
import com.yggdrasil.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO {

    @Autowired
    private final UserDatabase userDatabase;

    public UserDAO(UserDatabase userDatabase) {
        this.userDatabase = userDatabase;
    }


    public void createUser(Users users) {
        userDatabase.save(users);
    }


    public void getUser(Long id) {
        userDatabase.getById(id);
    }


    public void editUser(Long id, Users users) {
        Users user = userDatabase.findById(id).orElseThrow();

        user.setUsername(users.getUsername());
        user.setPassword(users.getPassword());
        user.setEmail(users.getEmail());
        user.setStreet(users.getStreet());
        user.setZipCode(users.getZipCode());

        userDatabase.save(user);
    }


    public void deleteUser(Long id) {
        userDatabase.deleteById(id);
    }
}
