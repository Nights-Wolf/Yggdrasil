package com.yggdrasil.api;

import com.yggdrasil.model.Transactions;
import com.yggdrasil.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/transaction")
public class TransactionRestController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public void createTransaction(@RequestBody Transactions transactions) {
        transactionService.createTransaction(transactions);
    }

    @PutMapping("{id}")
    public void editTransaction(@PathVariable("id") Long id, @RequestBody Transactions transactions) {
        transactionService.editTransaction(id, transactions);
    }

    @DeleteMapping("{id}")
    public void deleteTransaction(@PathVariable("id") Long id) {
        transactionService.deleteTransaction(id);
    }
}
