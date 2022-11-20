package com.yggdrasil.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

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

    @OneToMany(mappedBy = "shipmentsId")
    @JsonManagedReference
    private Set<Orders> orders;

    public Shipments() {super();}

    public Shipments(Long id, String name, float price, int shipmentDays) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.shipmentDays = shipmentDays;
    }
}
