package com.yggdrasil.mailing;

public interface EmailService {

    String sendSimpleMail(EmailDetails details);

    String sendMailWithAttachment(EmailDetails details);
}
