package com.yggdrasil.service;

import com.yggdrasil.DAO.RefreshTokenDAO;
import com.yggdrasil.model.RefreshToken;
import org.springframework.stereotype.Service;

@Service
public class RefreshTokenService {

    private final RefreshTokenDAO refreshTokenDAO;

    public RefreshTokenService(RefreshTokenDAO refreshTokenDAO) {
        this.refreshTokenDAO = refreshTokenDAO;
    }

    public void addToken(RefreshToken refreshToken) {
        refreshTokenDAO.addToken(refreshToken);
    }

    public void deleteToken(Long id) {
        refreshTokenDAO.deleteToken(id);
    }
}
