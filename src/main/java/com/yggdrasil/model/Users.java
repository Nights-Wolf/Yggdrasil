package com.yggdrasil.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Setter
@Getter
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String email;
    private String street;
    private String zipCode;

    public Users() {
        super();
    }

    public Users(String username, String password, String email, String street, String zipCode) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.street = street;
        this.zipCode = zipCode;
    }
}
