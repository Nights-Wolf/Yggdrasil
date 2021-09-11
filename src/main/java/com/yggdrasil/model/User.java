package com.yggdrasil.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Setter
@Getter
public class User {

    private Long id;

    private String username;

    public User(Long id, String username) {
        this.id = id;
        this.username = username;
    }
}
