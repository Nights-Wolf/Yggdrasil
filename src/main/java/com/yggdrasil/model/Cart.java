package com.yggdrasil.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.aspectj.weaver.ast.Or;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Setter
@Getter
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonBackReference
    private Users userId;
    private Date createdDate;
    private String token;

    @OneToOne(mappedBy = "cartId")
    @JsonManagedReference
    private Orders orders;

    @OneToMany(mappedBy = "cartId")
    @JsonManagedReference
    private Set<CartItem> cartItem;


    public Cart() {super();}

    public Cart(Long id, Users userId, Date createdDate, String token) {
        this.id = id;
        this.userId = userId;
        this.createdDate = createdDate;
        this.token = token;
    }
}
