package com.yggdrasil.service;

import com.yggdrasil.databaseInterface.TransactionDatabase;
import com.yggdrasil.model.Transactions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    private final TransactionDatabase transactionDatabase;

    @Autowired
    public TransactionService(TransactionDatabase transactionDatabase) {
        this.transactionDatabase = transactionDatabase;
    }

    public void createTransaction(Transactions transactions) {
        transactionDatabase.save(transactions);
    }

    public void editTransaction(Long id, Transactions transactions) {
        Transactions transaction = transactionDatabase.findById(id).orElseThrow();

        transaction.setTransactionNumber(transactions.getTransactionNumber());
        transaction.setTransactionValue(transactions.getTransactionValue());
        transaction.setItemId(transactions.getItemId());
        transaction.setUserId(transactions.getUserId());
        transaction.setTransactionDate(transactions.getTransactionDate());
        transaction.setStreet(transactions.getStreet());
        transaction.setZipCode(transactions.getZipCode());

        transactionDatabase.save(transaction);
    }

    public void deleteTransaction(Long id) {
        transactionDatabase.deleteById(id);
    }
}
