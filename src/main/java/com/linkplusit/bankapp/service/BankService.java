package com.linkplusit.bankapp.service;

import com.linkplusit.bankapp.model.Bank;

import java.util.List;

public interface BankService {
    Bank saveBank(Bank bank);
    List<Bank> getAllBanks();
    Bank updateBank(Integer id, Bank bank);
    void deleteBank(Integer id);
    double getTotalTransanctionFee();
    double getTotalTransferAmount();
    Bank addAccount(Integer bankId, Integer accountId);
}
