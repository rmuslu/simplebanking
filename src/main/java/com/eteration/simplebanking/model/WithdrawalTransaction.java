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
    public TransactionStatus apply(Account account) {
        try {
            this.setAccount(account);
            account.debit(amount);
        } catch (InsufficientBalanceException e) {
            status = new TransactionStatus("FAIL");
            return status;
        }
        status = new TransactionStatus("OK");
        return status;
    }
}
