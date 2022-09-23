package com.yggdrasil.service;

import com.yggdrasil.databaseInterface.RefreshTokenDatabase;
import com.yggdrasil.model.RefreshToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RefreshTokenService {

    private final RefreshTokenDatabase refreshTokenDatabase;

    @Autowired
    public RefreshTokenService(RefreshTokenDatabase refreshTokenDatabase) {
        this.refreshTokenDatabase = refreshTokenDatabase;
    }

    public void addToken(RefreshToken refreshToken) {
        refreshTokenDatabase.save(refreshToken);
    }

    public void deleteToken(Long id) {
        refreshTokenDatabase.deleteById(id);
    }
}
