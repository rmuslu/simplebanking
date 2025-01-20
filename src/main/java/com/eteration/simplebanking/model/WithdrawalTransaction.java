package com.eteration.simplebanking.model;


import com.eteration.simplebanking.model.exception.InsufficientBalanceException;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("WITHDRAWAL")
public class WithdrawalTransaction extends Transaction {
    public WithdrawalTransaction() {
        super();
    }
    public WithdrawalTransaction(double amount) {
        super(amount);
    }

    @Override
    public void apply(Account account) {
        if (account.getBalance() < getAmount()) {
            throw new InsufficientBalanceException("Insufficient balance for withdrawal");
        }
        account.setBalance(account.getBalance() - getAmount());
    }
}
