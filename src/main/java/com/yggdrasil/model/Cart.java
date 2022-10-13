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
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Date createdDate;
    private String token;

    public Cart() {super();}

    public Cart(Long id, Long userId, Date createdDate, String token) {
        this.id = id;
        this.userId = userId;
        this.createdDate = createdDate;
        this.token = token;
    }
}
