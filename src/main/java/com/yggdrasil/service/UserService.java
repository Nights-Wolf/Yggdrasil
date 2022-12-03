package com.yggdrasil.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yggdrasil.databaseInterface.UserDatabase;
import com.yggdrasil.model.ChangeEmail;
import com.yggdrasil.model.ChangePassword;
import com.yggdrasil.model.Users;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.OK;

@Service
public class UserService {


    private final UserDatabase userDatabase;
    private final PasswordEncoder passwordEncoder;
    private final String secret;

    @Autowired
    public UserService(UserDatabase userDatabase, PasswordEncoder passwordEncoder, @Value("$(jwt.secret)") String secret) {
        this.userDatabase = userDatabase;
        this.passwordEncoder = passwordEncoder;
        this.secret = secret;
    }

    public ResponseEntity<String> createUser(Users users) {
        Users checkIfUserExists = userDatabase.findByEmail(users.getEmail());
        boolean checkIfUserAcceptedTerms = users.isAcceptedTerms();
        boolean checkIfUserAcceptedRodo = users.isAcceptedRodo();

        if (checkIfUserExists != null) {
            String err = ("Ten email jest już używany!");
            return new ResponseEntity<>(err, HttpStatus.NOT_ACCEPTABLE);
        } else if (!checkIfUserAcceptedTerms || !checkIfUserAcceptedRodo) {
            String err = ("Musisz zaakceptować Regulamin i RODO!");
            return new ResponseEntity<>(err, HttpStatus.NOT_ACCEPTABLE);
        } else {
            users.setGrantedAuthorities("USER");
            users.setPassword(passwordEncoder.encode(users.getPassword()));
            users.setAccountNonExpired(true);
            users.setAccountNonLocked(true);
            users.setCredentialsNonExpired(true);
            users.setEnabled(true);
            userDatabase.save(users);

            return new ResponseEntity<>("User created", OK);
        }
    }

    public ResponseEntity<Users> getUser(HttpServletRequest request, HttpServletResponse response) {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String accessToken = authorizationHeader.substring("Bearer ".length());
                JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret)).build();
                DecodedJWT decodedJWT = verifier.verify(accessToken);
                String email = decodedJWT.getSubject();
                Users users = userDatabase.findByEmail(email);
                return new ResponseEntity<>(users, HttpStatus.OK);

            } catch (Exception exception) {
                response.setHeader("error", exception.getMessage());
                response.setStatus(FORBIDDEN.value());
            }
        }
        return ResponseEntity.status(FORBIDDEN).build();
    }

    public void editUser(Users users) {
        Users user = userDatabase.findByEmail(users.getEmail());

        user.setUsername(users.getUsername());
        user.setSurname(users.getSurname());
        user.setStreet(users.getStreet());
        user.setCity(users.getCity());
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

    public void grantAdmin(Long id) {
        Users users = userDatabase.findById(id).orElseThrow();

        //users.setGrantedAuthorities("ADMIN");
        userDatabase.save(users);
    }

    public Users findByEmail(String email) {
        return userDatabase.findByEmail(email);
    }

    public void remindPassword(String email, Users users) {
        Users user = userDatabase.findByEmail(email);

        user.setPassword(passwordEncoder.encode(users.getPassword()));

        userDatabase.save(user);
    }

    public ResponseEntity changePassword(ChangePassword changePassword) {
        Users user = userDatabase.findByEmail(changePassword.getEmail());

        String usersPassword = user.getPassword();
        String usersOldPassword = changePassword.getPassword();
        String usersNewPassword = changePassword.getNewPassword();

        if (passwordEncoder.matches(usersOldPassword, usersPassword) && !passwordEncoder.matches(usersNewPassword, usersPassword)) {
            user.setPassword(passwordEncoder.encode(changePassword.getNewPassword()));

            userDatabase.save(user);

            return ResponseEntity.status(HttpStatus.ACCEPTED.value()).build();
        } else if (passwordEncoder.matches(usersNewPassword, usersPassword)) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED.value()).build();
        }
        return ResponseEntity.status(FORBIDDEN.value()).build();
    }

    public ResponseEntity changeEmail(ChangeEmail changeEmail) {
        Users user = userDatabase.findByEmail(changeEmail.getEmail());
        String checkIfUserExists = String.valueOf(userDatabase.findByEmail(changeEmail.getNewEmail()));

        if (checkIfUserExists.equals("null") || checkIfUserExists.equals("")) {
            user.setEmail(changeEmail.getNewEmail());

            userDatabase.save(user);

            return ResponseEntity.status(HttpStatus.ACCEPTED.value()).build();
        }

        return ResponseEntity.status(HttpStatus.CONFLICT.value()).build();
    }

    public void rememberMeCheck(String email) {
        Users users = userDatabase.findByEmail(email);

        users.setRememberMe(true);

        userDatabase.save(users);
    }
}
