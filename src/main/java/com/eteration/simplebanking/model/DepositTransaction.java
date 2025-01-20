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
    public TransactionStatus apply(Account account) {
        this.setAccount(account);
        account.credit(amount);
        status = new TransactionStatus("OK");
        return status;
    }
}

