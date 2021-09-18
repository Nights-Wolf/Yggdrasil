package com.yggdrasil.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class Item {

    private Long id;

    private String itemName;
    private Date date;
    private int price;
    private int itemsLeft;

    public Item(Long id, String itemName, Date date, int price, int itemsLeft) {
        this.id = id;
        this.itemName = itemName;
        this.date = date;
        this.price = price;
        this.itemsLeft = itemsLeft;
    }
}
