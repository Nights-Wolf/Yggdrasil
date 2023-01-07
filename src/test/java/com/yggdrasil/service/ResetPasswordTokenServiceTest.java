package com.yggdrasil.service;

import com.yggdrasil.databaseInterface.ResetPasswordTokenDatabase;
import com.yggdrasil.model.ResetPasswordToken;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ResetPasswordTokenServiceTest {

    @Mock
    private ResetPasswordTokenDatabase resetPasswordTokenDatabase;

    @Mock
    private ResetPasswordTokenService resetPasswordTokenService;

    private Date date = new Date();
    private ResetPasswordToken resetPasswordToken = new ResetPasswordToken(1L, "dawianvi@wp.pl", "wakdopawkdaw", new Date(date.getTime() + (1000  * 60 * 60 * 24)));
    private ResetPasswordToken expiredResetPasswordToken = new ResetPasswordToken(1L, "dawianvi@wp.pl", "wakdopawkdaw", new Date());


    @BeforeEach
    void setUp() {
        resetPasswordTokenService = new ResetPasswordTokenService(resetPasswordTokenDatabase);
    }

    @AfterEach
    void tearDown() {
        resetPasswordTokenDatabase.deleteAll();
    }

    @Test
    void addResetPasswordToken() {
        Mockito.when(resetPasswordTokenDatabase.save(resetPasswordToken)).thenReturn(resetPasswordToken);

        resetPasswordTokenService.addResetPasswordToken(resetPasswordToken);

        ArgumentCaptor<ResetPasswordToken> resetPasswordTokenArgumentCaptor = ArgumentCaptor.forClass(ResetPasswordToken.class);

        Mockito.verify(resetPasswordTokenDatabase).save(resetPasswordTokenArgumentCaptor.capture());

        ResetPasswordToken capturedResetPasswordToken = resetPasswordTokenArgumentCaptor.getValue();

        Assertions.assertEquals(capturedResetPasswordToken, resetPasswordToken);
    }

    @Test
    void shouldReturn200() {
        Mockito.when(resetPasswordTokenDatabase.findByToken(resetPasswordToken.getToken())).thenReturn(resetPasswordToken);

        ResponseEntity<Void> response = resetPasswordTokenService.checkPassToken(resetPasswordToken.getToken());
        HttpStatus responseStatus = response.getStatusCode();
        HttpStatus statusOK = HttpStatus.OK;

        Assertions.assertEquals(statusOK, responseStatus);
    }

    @Test
    void shouldReturn302() {
        Mockito.when(resetPasswordTokenDatabase.findByToken(expiredResetPasswordToken.getToken())).thenReturn(expiredResetPasswordToken);

        ResponseEntity<Void> response = resetPasswordTokenService.checkPassToken(expiredResetPasswordToken.getToken());
        HttpStatus responseStatus = response.getStatusCode();
        HttpStatus statusFound = HttpStatus.FOUND;

        Assertions.assertEquals(statusFound, responseStatus);
    }
}