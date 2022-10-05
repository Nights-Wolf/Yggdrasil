package com.yggdrasil.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePassword {

    private String email;
    private String password;
    private String newPassword;

    public ChangePassword(){super();}

    public ChangePassword(String email, String password, String newPassword) {
        this.email = email;
        this.password = password;
        this.newPassword = newPassword;
    }
}
