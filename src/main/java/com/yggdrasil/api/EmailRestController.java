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

    @PostMapping("/sendMail")
    private String sendSimpleMail(@RequestBody EmailDetails emailDetails) {
        System.out.println("im working");
        String status = emailService.sendSimpleMail(emailDetails);

        return status;
    }
}
