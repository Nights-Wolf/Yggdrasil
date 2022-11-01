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
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long itemId;
    private int quantity;
    private Date createdDate;
    private Long cartId;

    public CartItem() {super();}

    public CartItem(Long id, Long itemId, int quantity, Date createdDate, Long cartId) {
        this.id = id;
        this.itemId = itemId;
        this.quantity = quantity;
        this.createdDate = createdDate;
        this.cartId = cartId;
    }
}
