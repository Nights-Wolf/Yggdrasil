package com.yggdrasil.service;

import com.yggdrasil.databaseInterface.ResetPasswordTokenDatabase;
import com.yggdrasil.model.ResetPasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
