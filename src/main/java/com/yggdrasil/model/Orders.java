package com.yggdrasil.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter
@Setter
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int orderValue;
    private Long itemId;
    private String itemName;
    private Long userId;
    private String username;
    private String surname;
    private String userEmail;
    private Date orderDate;
    private String street;
    private String zipCode;
    private String city;
    private String voivodeship;
    private String status;

    public Orders() {
        super();
    }

    public Orders(int orderValue, Long itemId, String itemName, Long userId, String username, String surname, String userEmail, Date orderDate, String street, String zipCode, String city,
                  String voivodeship, String status) {
        this.orderValue = orderValue;
        this.itemId = itemId;
        this.itemName = itemName;
        this.userId = userId;
        this.username = username;
        this.surname = surname;
        this.userEmail = userEmail;
        this.orderDate = orderDate;
        this.street = street;
        this.zipCode = zipCode;
        this.city = city;
        this.voivodeship = voivodeship;
        this.status = status;
    }
}
