package com.yggdrasil.api;

import com.yggdrasil.mailing.EmailDetails;
import com.yggdrasil.mailing.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/mail")
public class EmailRestController {

    private final EmailService emailService;

    @Autowired
    public EmailRestController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/sendMail/{token}")
    private String sendSimpleMail(@RequestBody EmailDetails emailDetails, @PathVariable String token) {
        EmailDetails email = emailDetails;

        email.setMsgBody("Witaj! \n\nAby zmienić hasło kliknij w link poniżej: \n" +  "http://localhost:8080/remindPassword/" + token + "\n\n Życzymy udanych zakupów, \nZespół Yggdrasil");

        String status = emailService.sendSimpleMail(emailDetails);

        return status;
    }
}
