package com.yggdrasil.service;

import com.yggdrasil.databaseInterface.RefreshTokenDatabase;
import com.yggdrasil.model.RefreshToken;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RefreshTokenServiceTest {

    @Mock
    private RefreshTokenDatabase refreshTokenDatabase;

    @Mock
    private RefreshTokenService refreshTokenService;

    @BeforeEach
    void setUp() {
        refreshTokenService = new RefreshTokenService(refreshTokenDatabase);
    }

    private RefreshToken refreshToken = new RefreshToken(1L, "jiowajdioawjdoiaw");

    @AfterEach
    void tearDown() {
        refreshTokenDatabase.deleteAll();
    }

    @Test
    void addToken() {
        Mockito.when(refreshTokenDatabase.save(refreshToken)).thenReturn(refreshToken);

        refreshTokenService.addToken(refreshToken);

        ArgumentCaptor<RefreshToken> refreshTokenArgumentCaptor = ArgumentCaptor.forClass(RefreshToken.class);
        Mockito.verify(refreshTokenDatabase).save(refreshTokenArgumentCaptor.capture());

        RefreshToken capturedRefreshToken = refreshTokenArgumentCaptor.getValue();

        Assertions.assertEquals(capturedRefreshToken, refreshToken);
    }

    @Test
    void deleteToken() {
        Mockito.when(refreshTokenDatabase.save(refreshToken)).thenReturn(refreshToken);

        refreshTokenService.addToken(refreshToken);

        refreshTokenService.deleteToken(refreshToken.getId());

        Mockito.verify(refreshTokenDatabase).deleteById(refreshToken.getId());
    }
}