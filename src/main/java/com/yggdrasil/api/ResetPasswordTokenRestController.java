package com.yggdrasil.api;

import com.yggdrasil.model.ResetPasswordToken;
import com.yggdrasil.service.ResetPasswordTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/check/{token}")
    private ResponseEntity<Void> checkPassToken(@PathVariable("token") String token) {
       return resetPasswordTokenService.checkPassToken(token);
    }
}
