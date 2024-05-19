package com.linkplusit.bankapp.service;

import com.linkplusit.bankapp.model.Account;
import com.linkplusit.bankapp.model.Bank;
import com.linkplusit.bankapp.model.Transaction;
import com.linkplusit.bankapp.repository.AccountRepository;
import com.linkplusit.bankapp.repository.BankRepository;
import com.linkplusit.bankapp.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    BankRepository bankRepository;

    private Double flatFee= 10.0;
    private Double percentFee= 5.0;

    @Override
    public void deposit(Integer id, double amount) {
        Account account= accountRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Account not found"+id));

        Bank bank = bankRepository.findById(account.getBank().getId())
                .orElseThrow(()-> new IllegalArgumentException("Account not found"+id));

        bank.setTotTransFee(+5.0);
        bankRepository.save(bank);

        account.setAccBalance(account.getAccBalance()+amount-flatFee);
        accountRepository.save(account);
    }

    @Override
    public void withdraw(Integer id, double amount) {
        Account account= accountRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Account not found"+id));

        if(account.getAccBalance()<amount+flatFee){
            throw new IllegalArgumentException("Insufficient funds!");
        }

        Bank bank = bankRepository.findById(account.getBank().getId())
                .orElseThrow(()-> new IllegalArgumentException("Account not found"+id));

        bank.setTotTransFee(+5.0);
        bankRepository.save(bank);

        account.setAccBalance(account.getAccBalance()-amount-flatFee);
        accountRepository.save(account);
    }

    @Override
    public void transfer(Integer originId, Integer resultId, Double amount, Boolean isFlat) {
        Account originAcc= accountRepository.findById(originId)
                .orElseThrow(()-> new IllegalArgumentException("Account not found"+originId));
        Account resultAcc= accountRepository.findById(resultId)
                .orElseThrow(()-> new IllegalArgumentException("Account not found"+resultId));

        double fee = isFlat ? flatFee : (percentFee / 100) * amount;
        String reason= isFlat ? "Flat Fee Transfer" :"Percent Fee Transfer";
        double totalAmount = amount + fee;

        if(originAcc.getAccBalance()<totalAmount){
            throw  new IllegalArgumentException("Insufficient founds!");
        }

        originAcc.setAccBalance(originAcc.getAccBalance()-totalAmount);
        resultAcc.setAccBalance(resultAcc.getAccBalance()+amount);

        Bank bank = bankRepository.findById(originAcc.getBank().getId())
                .orElseThrow(()-> new IllegalArgumentException("Account not found"+originId));

        bank.setTotTransFee(++fee);
        bankRepository.save(bank);


        accountRepository.save(originAcc);
        accountRepository.save(resultAcc);

        Transaction transaction= new Transaction(amount,originAcc,resultAcc,reason);

        transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getAllTransaction() {
        return transactionRepository.findAll();
    }

    @Override
    public List<Transaction> getTransactionsForAccount(Integer id) {
        List<Transaction> transactions= transactionRepository.findAll();

        // Filter transactions related to the account
        List<Transaction> filteredTransactions = transactions.stream()
                .filter(t -> t.getOriginAccount().getId()==id||t.getResultAccount().getId()==id)
                .collect(Collectors.toList());

        return filteredTransactions;
    }

}
