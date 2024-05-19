package com.linkplusit.bankapp.service;


import com.linkplusit.bankapp.model.Account;
import com.linkplusit.bankapp.model.Bank;
import com.linkplusit.bankapp.model.Transaction;
import com.linkplusit.bankapp.repository.AccountRepository;
import com.linkplusit.bankapp.repository.BankRepository;
import com.linkplusit.bankapp.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankServiceImpl implements BankService{



    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public Bank saveBank(Bank bank) {
        return bankRepository.save(bank);
    }

    @Override
    public List<Bank> getAllBanks() {
        return bankRepository.findAll();
    }

    @Override
    public Bank updateBank(Integer id, Bank bank) {
        Bank updateBank= bankRepository.findById(id)
                .orElseThrow(()-> new ExpressionException("Bank could not found !"+id));

        updateBank.setName(bank.getName());
        updateBank.setFlatFee(bank.getFlatFee());
        updateBank.setPercentFee(bank.getPercentFee());
        updateBank.setTotTransFee(bank.getTotTransFee());

        return bankRepository.save(updateBank);
    }

    @Override
    public void deleteBank(Integer id) {
        bankRepository.deleteById(id);
    }

    @Override
    public Bank addAccount(Integer bankId, Integer accountId) {
        Bank updateBank= bankRepository.findById(bankId)
                .orElseThrow(()-> new IllegalArgumentException("Bank not Found!"+bankId));
        Account account= accountRepository.findById(accountId)
                .orElseThrow(()-> new IllegalArgumentException("Bank not Found!"+accountId));

        updateBank.setAccount(account);
        return bankRepository.save(updateBank);
    }

    @Override
    public double getTotalTransanctionFee() {
        return transactionRepository.findAll().size()*5;
    }

    @Override
    public double getTotalTransferAmount() {
        return transactionRepository.findAll().stream().mapToDouble(Transaction::getAmount).sum();
    }
}
