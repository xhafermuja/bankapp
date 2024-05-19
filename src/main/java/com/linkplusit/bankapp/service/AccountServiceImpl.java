package com.linkplusit.bankapp.service;

import com.linkplusit.bankapp.model.Account;
import com.linkplusit.bankapp.model.Bank;
import com.linkplusit.bankapp.repository.AccountRepository;
import com.linkplusit.bankapp.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private BankRepository bankRepository;

    @Override
    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Account updateAccount(Integer id, Account account) {
        Account updateAcc= accountRepository.findById(id)
                .orElseThrow(()-> new ExpressionException("Account could not be foud"+ id));

        updateAcc.setAccBalance(account.getAccBalance());
        updateAcc.setNameOfUser(account.getNameOfUser());

        return accountRepository.save(updateAcc);
    }

    @Override
    public Double checkBalance(Integer id) {
        Account account= accountRepository.findById(id)
                .orElseThrow(()-> new ExpressionException("Account not found "+id));
        return account.getAccBalance();
    }

    @Override
    public void deleteAccount(Integer id) {
        accountRepository.deleteById(id);
    }

    @Override
    public Account assignBank(Integer accountId, Integer bankId) {
        Account account= accountRepository.findById(accountId)
                .orElseThrow(()->new ExpressionException("Lenda not exist with this id: "+ accountId));
        Bank bank= bankRepository.findById(bankId)
                .orElseThrow(()->new ExpressionException("Lenda not exist with this id: "+ bankId));
        account.setBank(bank);

        return accountRepository.save(account);
    }


}
