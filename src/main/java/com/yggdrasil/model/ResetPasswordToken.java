package com.yggdrasil.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Setter
@Getter
public class ResetPasswordToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String token;
    private Date expirationDate;

    public ResetPasswordToken() {super();}

    public ResetPasswordToken(String email, String token, Date expirationDate) {
        this.email = email;
        this.token = token;
        this.expirationDate = expirationDate;
    }
}
