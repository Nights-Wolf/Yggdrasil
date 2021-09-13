package com.yggdrasil.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Transaction {

    private Long id;

    private long transactionNumber;
    private int value;
    private Long itemId;
    private Long itemCategoryId;
    private Long userId;
    private Date transactionDate;
    private String address;

    public Transaction(Long id, long transactionNumber, int value, Long itemId, Long itemCategoryId, Long userId, Date transactionDate, String address) {
        this.id = id;
        this.transactionNumber = transactionNumber;
        this.value = value;
        this.itemId = itemId;
        this.itemCategoryId = itemCategoryId;
        this.userId = userId;
        this.transactionDate = transactionDate;
        this.address = address;
    }
}
