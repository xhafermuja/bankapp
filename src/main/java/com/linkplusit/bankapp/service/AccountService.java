package com.linkplusit.bankapp.service;

import com.linkplusit.bankapp.model.Account;

import java.util.List;

public interface AccountService {

    Account saveAccount(Account account);
    List<Account> getAllAccounts();
    Account updateAccount(Integer id, Account account);
    Double checkBalance(Integer id);
    void deleteAccount(Integer id);
    Account assignBank(Integer accountId ,Integer bankId);

}
