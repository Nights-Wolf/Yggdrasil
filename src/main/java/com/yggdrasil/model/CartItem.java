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
        property = "id", scope = CartItem.class)
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "item_Id", referencedColumnName = "id")
    private Item itemId;
    private int quantity;
    private Date createdDate;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "cart_Id", referencedColumnName = "id")
    private Cart cartId;

    public CartItem() {super();}

    public CartItem(Long id, Item itemId, int quantity, Date createdDate, Cart cartId) {
        this.id = id;
        this.itemId = itemId;
        this.quantity = quantity;
        this.createdDate = createdDate;
        this.cartId = cartId;
    }
}
