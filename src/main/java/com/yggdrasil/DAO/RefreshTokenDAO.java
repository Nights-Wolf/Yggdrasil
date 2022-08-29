package com.yggdrasil.DAO;

import com.yggdrasil.databaseInterface.RefreshTokenDatabase;
import com.yggdrasil.model.RefreshToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RefreshTokenDAO {

    private final RefreshTokenDatabase refreshTokenDatabase;

    @Autowired
    public RefreshTokenDAO(RefreshTokenDatabase refreshTokenDatabase) {
        this.refreshTokenDatabase = refreshTokenDatabase;
    }

    public void addToken(RefreshToken refreshToken) {
        refreshTokenDatabase.save(refreshToken);
    }

    public void deleteToken(Long id) {
        refreshTokenDatabase.deleteById(id);
    }
}
