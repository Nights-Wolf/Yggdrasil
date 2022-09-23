package com.yggdrasil.service;

import com.yggdrasil.databaseInterface.ResetPasswordTokenDatabase;
import com.yggdrasil.model.ResetPasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.header.Header;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.Date;

@Service
public class ResetPasswordTokenService {

    private final ResetPasswordTokenDatabase resetPasswordTokenDatabase;

    @Autowired
    public ResetPasswordTokenService(ResetPasswordTokenDatabase resetPasswordTokenDatabase) {
        this.resetPasswordTokenDatabase = resetPasswordTokenDatabase;
    }

    public void addResetPasswordToken(ResetPasswordToken resetPasswordToken) {
        resetPasswordTokenDatabase.save(resetPasswordToken);
    }

    public ResponseEntity<Void> checkPassToken(String token) {
        ResetPasswordToken resetPasswordToken = resetPasswordTokenDatabase.findByToken(token);

        if (resetPasswordToken != null) {
            Date expirationTime = resetPasswordToken.getExpirationDate();
            Date currentTime = new Date();
            if (currentTime.getTime() > expirationTime.getTime()) {
                return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("http://localhost:8080/passResetExpired")).build();
            }

            return ResponseEntity.ok().header("user", resetPasswordToken.getEmail()).build();
        }
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("http://localhost:8080/passResetNotFound")).build();
    }
}
