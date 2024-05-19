package com.linkplusit.bankapp.service;

import com.linkplusit.bankapp.model.Bank;
import com.linkplusit.bankapp.model.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionService {
    void deposit(Integer id, double amount);
    void withdraw(Integer id, double amount);
    void transfer(Integer originId, Integer resultId, Double amount, Boolean isFlat);
    List<Transaction> getAllTransaction();
    List<Transaction> getTransactionsForAccount(Integer id);
}
