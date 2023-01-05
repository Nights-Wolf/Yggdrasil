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
@Setter
@Getter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id", scope = Item.class)
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemName;

    private String image;
    private Date created;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "category_Id", referencedColumnName = "id")
    private Category categoryId;
    private int price;
    private String description;
    private int itemsLeft;

    public Item(){
        super();
    }

    public Item(Long id, String itemName, String image, Date created, Category categoryId, int price, String description, int itemsLeft) {
        this.id = id;
        this.itemName = itemName;
        this.image = image;
        this.created = created;
        this.categoryId = categoryId;
        this.price = price;
        this.description = description;
        this.itemsLeft = itemsLeft;
    }
}
