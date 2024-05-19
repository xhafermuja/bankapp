package com.linkplusit.bankapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double amount;

    @ManyToOne
    @JoinColumn(name = "origin_id")
    private Account originAccount;

    @ManyToOne
    @JoinColumn(name = "result_id")
    private Account resultAccount;
    private String reason;

    public Transaction() {}
    public Transaction(double amount, Account originAccount, Account resultAccount, String reason) {
        this.amount = amount;
        this.originAccount = originAccount;
        this.resultAccount = resultAccount;
        this.reason = reason;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Account getOriginAccount() {
        return originAccount;
    }

    public void setOriginAccount(Account originAccount) {
        this.originAccount = originAccount;
    }

    public Account getResultAccount() {
        return resultAccount;
    }

    public void setResultAccount(Account resultAccount) {
        this.resultAccount = resultAccount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
