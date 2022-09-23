package com.yggdrasil.service;

import com.yggdrasil.mailing.EmailDetails;
import com.yggdrasil.mailing.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.internet.MimeMessage;
import java.util.Locale;
import java.util.Map;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;
    private String sender;

    @Autowired
    public EmailServiceImpl(JavaMailSender javaMailSender, @Value("${spring.mail.username")String sender) {
        this.javaMailSender = javaMailSender;
        this.sender = sender;
    }

    @Override
    public String sendSimpleMail(EmailDetails details) {
        try {
            MimeMessage mailMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mailMessage);

            helper.setFrom(sender);
            helper.setTo(details.getRecipient());
            helper.setText(details.getMsgBody());
            helper.setSubject(details.getSubject());

            javaMailSender.send(mailMessage);

            return "Mail sent successfully";

        } catch (Exception e) {
            return "Error while sending Mail";
        }
    }

    @Override
    public String sendMailWithAttachment(EmailDetails details) {
        return null;
    }

}
