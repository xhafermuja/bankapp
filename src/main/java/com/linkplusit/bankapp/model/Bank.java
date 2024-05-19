package com.linkplusit.bankapp.model;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    @OneToMany(mappedBy = "bank")
    private List<Account> accountList;
    private double totTransFee;
    private double totTransferAmount;
    private double flatFee;
    private double percentFee;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccount(Account account) {
        accountList.add(account);
    }

    public double getTotTransferAmount() {
        return totTransferAmount;
    }

    public void setTotTransferAmount(double totTransferAmount) {
        this.totTransferAmount = totTransferAmount;
    }

    public double getTotTransFee() {
        return totTransFee;
    }

    public void setTotTransFee(double totTransFee) {
        this.totTransFee = totTransFee;
    }

    public double getFlatFee() {
        return flatFee;
    }

    public void setFlatFee(double flatFee) {
        this.flatFee = flatFee;
    }

    public double getPercentFee() {
        return percentFee;
    }

    public void setPercentFee(double percentFee) {
        this.percentFee = percentFee;
    }
}
