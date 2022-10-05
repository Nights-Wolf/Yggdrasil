package com.yggdrasil.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangeEmail {

    private String email;

    private String newEmail;

    public ChangeEmail(){super();}

    public ChangeEmail(String email, String newEmail) {
        this.email = email;
        this.newEmail = newEmail;
    }
}
