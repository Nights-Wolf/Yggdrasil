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
public class Shipments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private float price;
    private int shipmentDays;

    public Shipments() {super();}

    public Shipments(Long id, String name, float price, int shipmentDays) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.shipmentDays = shipmentDays;
    }
}
