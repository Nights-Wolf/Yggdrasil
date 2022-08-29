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
        String checkIfPasswordIsCorrect = users.getPassword();
        String checkIfNameIsCorrect = users.getUsername();
        String checkIfSurnameIsCorrect = users.getSurname();

        if (checkIfUserExists.equals("null") || !checkIfUserExists.equals("") && checkIfUserAcceptedTerms && checkIfUserAcceptedRodo &&
        checkIfPasswordIsCorrect != null || !checkIfPasswordIsCorrect.equals("") && !checkIfNameIsCorrect.equals("") && !checkIfSurnameIsCorrect.equals("")) {
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


    public Users getUser(Long id) {
       Users users = userDatabase.getById(id);
       return users;
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

    public void setToken(Long id, Long refreshToken) {
        Users user = userDatabase.findById(id).orElseThrow();

        user.setRefreshToken(refreshToken);

        userDatabase.save(user);
    }

    public void deleteToken(Long id) {
        Users user = userDatabase.findById(id).orElseThrow();

        user.setRefreshToken(null);

        userDatabase.save(user);
    }


    public void deleteUser(Long id) {
        userDatabase.deleteById(id);
    }

    public void grantAdmin(Long id) {
        Users users = userDatabase.findById(id).orElseThrow();

        //users.setGrantedAuthorities("ADMIN");
        userDatabase.save(users);
    }

    public Users findByEmail(String email) {
        return userDatabase.findByEmail(email);
    }

    public void rememberMeCheck(String email) {
        Users users = findByEmail(email);

        users.setRememberMe(true);

        userDatabase.save(users);
    }
}
