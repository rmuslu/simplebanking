package com.eteration.simplebanking.model;


import com.eteration.simplebanking.common.BillTypes;

import javax.persistence.*;

@Entity
@DiscriminatorValue("BILL_PAYMENT")
public class BillPaymentTransaction extends Transaction {

    @Enumerated(EnumType.STRING)
    @Column(name = "bill_type", nullable = true)
    private BillTypes billType;

    @Column(name = "reference", nullable = true)
    private String reference;

    public BillPaymentTransaction() {
        super();
    }

    public BillPaymentTransaction(double amount, BillTypes billType, String reference) {
        super(amount);
        this.billType = billType;
        this.reference = reference;
    }

    @Override
    public void apply(Account account) {
        if (account.getBalance() < this.getAmount()) {
            throw new IllegalArgumentException("Insufficient balance for bill payment.");
        }
        account.setBalance(account.getBalance() - this.getAmount());
    }

    public BillTypes getBillType() {
        return billType;
    }

    public void setBillType(BillTypes billType) {
        this.billType = billType;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
