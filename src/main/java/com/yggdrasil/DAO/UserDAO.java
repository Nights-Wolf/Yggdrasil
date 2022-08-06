package com.yggdrasil.DAO;

import com.yggdrasil.databaseInterface.UserDatabase;
import com.yggdrasil.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO {

    private final PasswordEncoder passwordEncoder;

    private final UserDatabase userDatabase;

    @Autowired
    public UserDAO(UserDatabase userDatabase, PasswordEncoder passwordEncoder) {
        this.userDatabase = userDatabase;
        this.passwordEncoder = passwordEncoder;
    }


    public void createUser(Users users) {
        String checkIfUserExists = String.valueOf(userDatabase.findByEmail(users.getEmail()));
        boolean checkIfUserAcceptedTerms = users.isAcceptedTerms();
        boolean checkIfUserAcceptedRodo = users.isAcceptedRodo();
        String checkIfPasswordIsCorrect = String.valueOf(users.getPassword());

        if (checkIfUserExists.equals("null") && checkIfUserAcceptedTerms && checkIfUserAcceptedRodo &&
        checkIfPasswordIsCorrect != null || !checkIfPasswordIsCorrect.equals("")) {
            users.setGrantedAuthorities("USER");
            users.setPassword(passwordEncoder.encode(users.getPassword()));
            users.setAccountNonExpired(true);
            users.setAccountNonLocked(true);
            users.setCredentialsNonExpired(true);
            users.setEnabled(true);
            userDatabase.save(users);
        } else {
            System.out.println("This email is already in use! You must accept terms! You must accept RODO!");
        }
    }


    public void getUser(Long id) {
        userDatabase.getById(id);
    }


    public void editUser(Long id, Users users) {
        Users user = userDatabase.findById(id).orElseThrow();

        user.setUsername(users.getUsername());
        user.setSurname(users.getSurname());
        user.setPassword(users.getPassword());
        user.setEmail(users.getEmail());
        user.setStreet(users.getStreet());
        user.setZipCode(users.getZipCode());
        user.setVoivodeship(users.getVoivodeship());

        userDatabase.save(user);
    }


    public void deleteUser(Long id) {
        userDatabase.deleteById(id);
    }

    public void grantAdmin(Long id) {
        Users users = userDatabase.findById(id).orElseThrow();

        users.setGrantedAuthorities("ADMIN");
        userDatabase.save(users);
    }
}
