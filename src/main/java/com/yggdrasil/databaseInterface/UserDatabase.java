package com.yggdrasil.databaseInterface;

import com.yggdrasil.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDatabase  extends JpaRepository<Users, Long> {

    Users findByUsername(String username);

    Users findByEmail(String email);
}
