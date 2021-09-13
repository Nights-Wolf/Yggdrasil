package com.yggdrasil.databaseInterface;

import com.yggdrasil.model.Transaction;

public interface TransactionDatabase {

    void createTransaction(Transaction transaction);
    void editTransaction(Long id, Transaction transaction);
    void deleteTransaction(Long id);
}