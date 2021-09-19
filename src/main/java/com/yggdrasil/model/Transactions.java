package com.yggdrasil.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter
@Setter
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long transactionNumber;
    private int transactionValue;
    private Long itemId;
    private Long userId;
    private String userEmail;
    private Date transactionDate;
    private String street;
    private String zipCode;

    public Transactions() {
        super();
    }

    public Transactions(long transactionNumber, int transactionValue, Long itemId, Long userId, String userEmail, Date transactionDate, String street, String zipCode) {
        this.transactionNumber = transactionNumber;
        this.transactionValue = transactionValue;
        this.itemId = itemId;
        this.userId = userId;
        this.userEmail = userEmail;
        this.transactionDate = transactionDate;
        this.street = street;
        this.zipCode = zipCode;
    }
}
