package com.linkplusit.bankapp.controller;


import com.linkplusit.bankapp.model.Bank;
import com.linkplusit.bankapp.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bank")
@CrossOrigin
public class BankController {
    @Autowired
    private BankService bankService;
    @GetMapping("/getAll")
    private List<Bank> getAllBanks(){
        return bankService.getAllBanks();
    }

    @PostMapping("/save")
    private Bank saveBank(@RequestBody Bank bank){
        return bankService.saveBank(bank);
    }

    @PutMapping("/{id}")
    private Bank updateBank(@PathVariable("id") Integer id, @RequestBody Bank bank){
        return bankService.updateBank(id,bank);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<String> deleteBank(@PathVariable("id") Integer id){
        bankService.deleteBank(id);

        return ResponseEntity.ok("Bank deleted Successfully!");
    }

    @GetMapping("/totalTransactionFee")
    private double getTotalTransanctionFee(){
        return bankService.getTotalTransanctionFee();
    }

    @GetMapping("/totalTransferAmount")
    private double getTotalTransferAmount(){
        return bankService.getTotalTransferAmount();
    }





}
