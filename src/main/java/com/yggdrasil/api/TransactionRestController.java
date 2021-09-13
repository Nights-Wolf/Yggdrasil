package com.yggdrasil.api;

import com.yggdrasil.model.Transaction;
import com.yggdrasil.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transaction")
public class TransactionRestController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public void createTransaction(@RequestBody Transaction transaction) {
        transactionService.createTransaction(transaction);
    }

    @PutMapping("{id}")
    public void editTransaction(@PathVariable("id") Long id, @RequestBody Transaction transaction) {
        transactionService.editTransaction(id, transaction);
    }

    @DeleteMapping("{id}")
    public void deleteTransaction(@PathVariable("id") Long id) {
        transactionService.deleteTransaction(id);
    }
}
