package com.yggdrasil.model;

import com.auth0.jwt.JWT;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Setter
@Getter
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    public RefreshToken() {
        super();
    }

    public RefreshToken(Long id, String token) {
        this.id = id;
        this.token = token;
    }
}
