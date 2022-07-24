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

    private String image;
    private Date created;
    private int categoryId;
    private int price;
    private int itemsLeft;

    public Item(){
        super();
    }

    public Item(String itemName, String image, Date created, int categoryId, int price, int itemsLeft) {
        this.itemName = itemName;
        this.image = image;
        this.created = created;
        this.categoryId = categoryId;
        this.price = price;
        this.itemsLeft = itemsLeft;
    }
}
