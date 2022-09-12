package com.yggdrasil.api;

import com.yggdrasil.model.ResetPasswordToken;
import com.yggdrasil.service.ResetPasswordTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/resetPasswordToken")
public class ResetPasswordTokenRestController {

    private final ResetPasswordTokenService resetPasswordTokenService;

    @Autowired
    public ResetPasswordTokenRestController(ResetPasswordTokenService resetPasswordTokenService) {
        this.resetPasswordTokenService = resetPasswordTokenService;
    }

    @PostMapping("/add")
    private void addResetPasswordToken(@RequestBody ResetPasswordToken resetPasswordToken) {
        resetPasswordTokenService.addResetPasswordToken(resetPasswordToken);
    }
}
