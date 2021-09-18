package com.yggdrasil.service;

import com.yggdrasil.DAO.TransactionDAO;
import com.yggdrasil.model.Transactions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    private TransactionDAO transactionDAO;

    public void createTransaction(Transactions transactions) {
        transactionDAO.createTransaction(transactions);
    }

    public void editTransaction(Long id, Transactions transactions) {
        transactionDAO.editTransaction(id, transactions);
    }

    public void deleteTransaction(Long id) {
        transactionDAO.deleteTransaction(id);
    }
}
