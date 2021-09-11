package com.yggdrasil.databaseInterface;

import com.yggdrasil.model.Transaction;

public interface TransactionDatabase {

    void createTransaction(Transaction transaction);
    void deleteTransaction(String transactionId, Transaction transaction);
}