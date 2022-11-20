package com.yggdrasil.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Setter
@Getter
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    @JsonBackReference
    private Item itemId;
    private int quantity;
    private Date createdDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_Id")
    @JsonBackReference
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
