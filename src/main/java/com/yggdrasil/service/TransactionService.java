package com.yggdrasil.service;

import com.yggdrasil.DAO.FakeTransactionDAO;
import com.yggdrasil.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    private FakeTransactionDAO fakeTransactionDAO;

    public void createTransaction(Transaction transaction) {
        fakeTransactionDAO.createTransaction(transaction);
    }

    public void editTransaction(Long id, Transaction transaction) {
        fakeTransactionDAO.editTransaction(id, transaction);
    }

    public void deleteTransaction(Long id) {
        fakeTransactionDAO.deleteTransaction(id);
    }
}
