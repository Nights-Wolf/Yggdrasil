package com.yggdrasil.api;

import com.yggdrasil.mailing.EmailDetails;
import com.yggdrasil.mailing.EmailService;
import com.yggdrasil.service.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.net.URL;

@RestController
@RequestMapping("api/mail")
public class EmailRestController {

    private final EmailServiceImpl emailService;
    private URL url;

    public EmailRestController(EmailServiceImpl emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/sendMail/{token}")
    private String sendSimpleMail(@RequestBody EmailDetails emailDetails, @PathVariable String token) throws MalformedURLException {
        EmailDetails email = emailDetails;
        url = new URL("http://localhost:8080/remindPassword/");

        email.setMsgBody("Witaj! \n\nAby zmienić hasło kliknij w link poniżej: \n" + url + token + "\n\n Życzymy udanych zakupów, \nZespół Yggdrasil");

        String status = emailService.sendSimpleMail(emailDetails);

        return status;
    }
}
