package com.yggdrasil.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id", nullable = false)
    @JsonBackReference
    private Category categoryId;
    private int price;
    private String description;
    private int itemsLeft;

    @OneToOne(mappedBy = "itemId")
    @JsonManagedReference
    private CartItem cartItem;

    public Item(){
        super();
    }

    public Item(String itemName, String image, Date created, Category categoryId, int price, String description, int itemsLeft) {
        this.itemName = itemName;
        this.image = image;
        this.created = created;
        this.categoryId = categoryId;
        this.price = price;
        this.description = description;
        this.itemsLeft = itemsLeft;
    }
}
