package com.eteration.simplebanking.dto;

import com.eteration.simplebanking.common.BillTypes;
import com.eteration.simplebanking.model.BillPaymentTransaction;
import com.eteration.simplebanking.model.Transaction;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TransactionDTO {
    private LocalDateTime date;
    private double amount;
    private String type;
    private BillTypes billType;
    private String reference;


    public TransactionDTO() {
    }

    public TransactionDTO(Transaction transaction) {
        this.date = transaction.getDate();
        this.amount = transaction.getAmount();
        this.type = transaction.getClass().getSimpleName();

        if (transaction instanceof BillPaymentTransaction) {
            BillPaymentTransaction billTransaction = (BillPaymentTransaction) transaction;
            this.billType = billTransaction.getBillType();
            this.reference = billTransaction.getReference();
        }
    }

}

