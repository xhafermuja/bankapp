package com.linkplusit.bankapp.controller;


import com.linkplusit.bankapp.model.Account;
import com.linkplusit.bankapp.model.Bank;
import com.linkplusit.bankapp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/account")
@CrossOrigin
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping
    private String hello(){return "Hello from rest Services";}

    @GetMapping("/getAll")
    private List<Account> accountList(){
        return accountService.getAllAccounts();
    }

    @PostMapping("/save")
    private Account saveAccount(@RequestBody Account account){
        return accountService.saveAccount(account);
    }

    @PutMapping("/{id}")
    private Account updateAccount(@PathVariable("id") Integer id, @RequestBody Account account){
        return accountService.updateAccount(id,account);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<String> deleteAccount(@PathVariable("id") Integer id){
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Department deleted successfully!");
    }

    @GetMapping("/balance/{id}")
    private Double checkBalance(@PathVariable Integer id){
        return  accountService.checkBalance(id);
    }

    @PutMapping("/{accountId}/bank/{bankId}")
    private Account assignBank(@PathVariable Integer accountId, @PathVariable Integer bankId){
        return accountService.assignBank(accountId,bankId);
    }


}
