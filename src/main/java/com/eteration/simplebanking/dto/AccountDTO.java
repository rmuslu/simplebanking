package com.eteration.simplebanking.dto;

import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.Transaction;

import java.util.ArrayList;
import java.util.List;

public class AccountDTO {
    private String accountNumber;
    private String owner;
    private double balance;
    private List<TransactionDTO> transactions;

    public AccountDTO(Account account) {
        this.accountNumber = account.getAccountNumber();
        this.owner = account.getOwner();
        this.balance = account.getBalance();
        this.transactions = new ArrayList<>();


        for (Transaction transaction : account.getTransactions()) {
            this.transactions.add(new TransactionDTO(transaction));
        }
    }
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<TransactionDTO> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionDTO> transactions) {
        this.transactions = transactions;
    }
}
