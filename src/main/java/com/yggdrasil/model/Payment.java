package com.yggdrasil.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.criterion.Order;

import javax.persistence.*;
import java.util.Set;

@Entity
@Setter
@Getter
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "paymentId")
    @JsonManagedReference
    private Set<Orders> orders;

    public Payment() {super();}

    public Payment(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
