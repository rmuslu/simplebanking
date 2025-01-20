package com.eteration.simplebanking.mapper;

import com.eteration.simplebanking.common.BillTypes;
import com.eteration.simplebanking.dto.AccountDTO;
import com.eteration.simplebanking.dto.TransactionDTO;
import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.BillPaymentTransaction;
import com.eteration.simplebanking.model.Transaction;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountMapper {

    public AccountDTO toDTO(Account account) {
        AccountDTO dto = new AccountDTO(account);
        dto.setAccountNumber(account.getAccountNumber());
        dto.setOwner(account.getOwner());
        dto.setBalance(account.getBalance());

        List<TransactionDTO> transactions = new ArrayList<>();
        for (Transaction transaction : account.getTransactions()) {
            transactions.add(toTransactionDTO(transaction));
        }
        dto.setTransactions(transactions);

        return dto;
    }

    private TransactionDTO toTransactionDTO(Transaction transaction) {
        TransactionDTO dto = new TransactionDTO();
        dto.setDate(transaction.getDate());
        dto.setAmount(transaction.getAmount());
        dto.setType(transaction.getClass().getSimpleName());
        if (transaction instanceof BillPaymentTransaction) {
            BillPaymentTransaction billTransaction = (BillPaymentTransaction) transaction;
            dto.setBillType(BillTypes.valueOf(billTransaction.getBillType().name()));
            dto.setReference(billTransaction.getReference());
        }
        return dto;
    }
}
