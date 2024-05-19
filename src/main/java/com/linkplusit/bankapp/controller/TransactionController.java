package com.linkplusit.bankapp.controller;

import com.linkplusit.bankapp.model.Transaction;
import com.linkplusit.bankapp.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/transaction")
@CrossOrigin
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/deposit/{id}")
    private ResponseEntity<String> deposit(@PathVariable Integer id, @RequestParam double amount){
        transactionService.deposit(id,amount);
        return ResponseEntity.ok("Deposit Completed!");
    }

    @PostMapping("/withdraw/{id}")
    private ResponseEntity<String> withdraw(@PathVariable Integer id, @RequestParam double amount){
        transactionService.withdraw(id,amount);
        return ResponseEntity.ok("Withdraw Completed!");
    }

    @PostMapping("/transfer")
    private ResponseEntity<String> transfer(@RequestParam Integer originId, @RequestParam Integer resultId,
                                    @RequestParam Double amount, @RequestParam boolean isFlat){

        transactionService.transfer(originId,resultId,amount,isFlat);

        return ResponseEntity.ok("Transfer Completed!");
    }

    @GetMapping("/getAll")
    private List<Transaction> getAllTransactions(){
        return transactionService.getAllTransaction();
    }

    @GetMapping("/getAll/{id}")
    private List<Transaction> getTransactionsForAccount(@PathVariable Integer id) {
        return transactionService.getTransactionsForAccount(id);
    }
}

