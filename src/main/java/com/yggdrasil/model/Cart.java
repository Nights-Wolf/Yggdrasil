package com.yggdrasil.model;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;
import org.aspectj.weaver.ast.Or;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Setter
@Getter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id", scope = Cart.class)
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Users userId;
    private Date createdDate;
    private String token;

    @OneToOne(mappedBy = "cartId")
    private Orders orders;


    public Cart() {super();}

    public Cart(Long id, Users userId, Date createdDate, String token) {
        this.id = id;
        this.userId = userId;
        this.createdDate = createdDate;
        this.token = token;
    }
}
