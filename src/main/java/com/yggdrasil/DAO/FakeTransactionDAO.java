package com.yggdrasil.DAO;

import com.yggdrasil.databaseInterface.TransactionDatabase;
import com.yggdrasil.model.Transaction;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class FakeTransactionDAO implements TransactionDatabase {

    HashMap<Long, Transaction> fakeTransactionRepository = new HashMap<>();

    @Override
    public void createTransaction(Transaction transaction) {
        fakeTransactionRepository.put(transaction.getId(), transaction);
    }

    @Override
    public void editTransaction(Long id, Transaction transaction) {
        fakeTransactionRepository.replace(id, transaction);
    }

    @Override
    public void deleteTransaction(Long id) {
        fakeTransactionRepository.remove(id);
    }
}
