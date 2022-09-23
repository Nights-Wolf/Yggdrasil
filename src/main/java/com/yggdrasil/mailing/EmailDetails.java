package com.yggdrasil.mailing;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class EmailDetails {

    private String recipient;
    private String msgBody;
    private String subject;
    private String attachment;

    public EmailDetails() {super();}

    public EmailDetails(String recipient, String msgBody, String subject, String attachment) {
        this.recipient = recipient;
        this.msgBody = msgBody;
        this.subject = subject;
        this.attachment = attachment;
    }
}
