package com.eteration.simplebanking.model;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("DEPOSIT")
public class DepositTransaction extends Transaction {
    public DepositTransaction(double amount) {
        super(amount);
    }

    public DepositTransaction() {
        super(0.0);
    }

    @Override
    public void apply(Account account) {
        account.setBalance(account.getBalance() + getAmount());
    }
}

