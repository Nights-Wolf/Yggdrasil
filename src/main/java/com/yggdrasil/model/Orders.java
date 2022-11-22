package com.yggdrasil.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id", scope = Orders.class)
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int orderValue;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id")
    private Cart cartId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private Users userId;
    private String username;
    private String surname;
    private String userEmail;
    private Date orderDate;
    private String street;
    private String zipCode;
    private String city;
    private String voivodeship;
    private String status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shipments_Id")
    private Shipments shipmentsId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "paymentId")
    private Payment paymentId;

    public Orders() {
        super();
    }

    public Orders(int orderValue, Cart cartId, Users userId, String username, String surname, String userEmail, Date orderDate, String street, String zipCode, String city,
                  String voivodeship, String status, Shipments shipmentsId, Payment paymentId) {
        this.orderValue = orderValue;
        this.cartId = cartId;
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
        this.shipmentsId = shipmentsId;
        this.paymentId = paymentId;
    }
}
