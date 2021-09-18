package com.yggdrasil.DAO;

import com.yggdrasil.databaseInterface.TransactionDatabase;
import com.yggdrasil.model.Transactions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TransactionDAO {

    @Autowired
    private final TransactionDatabase transactionDatabase;

    public TransactionDAO(TransactionDatabase transactionDatabase) {
        this.transactionDatabase = transactionDatabase;
    }

    public void createTransaction(Transactions transactions) {
        transactionDatabase.save(transactions);
    }

    public void editTransaction(Long id, Transactions transactions) {
    }

    public void deleteTransaction(Long id) {
        transactionDatabase.deleteById(id);
    }
}
