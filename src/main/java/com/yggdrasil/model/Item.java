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
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemName;
    private Date created;
    private String category;
    private int price;
    private int itemsLeft;

    public Item(String itemName, Date date, int price, int itemsLeft) {
        this.itemName = itemName;
        this.created = date;
        this.price = price;
        this.itemsLeft = itemsLeft;
    }
}
