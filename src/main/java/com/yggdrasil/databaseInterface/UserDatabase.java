package com.yggdrasil.databaseInterface;

import com.yggdrasil.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDatabase  {

    void createUser(User user);
    void getUser(Long id);
    void editUser(Long id, User user);
    void deleteUser(Long id);
}
