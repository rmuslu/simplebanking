package com.eteration.simplebanking.services;

import com.eteration.simplebanking.common.BillTypes;
import com.eteration.simplebanking.model.*;
import com.eteration.simplebanking.repository.AccountRepository;
import com.eteration.simplebanking.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountService accountService;
    private final AccountRepository accountRepository;

    public TransactionService(TransactionRepository transactionRepository, AccountService accountService, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountService = accountService;
        this.accountRepository = accountRepository;
    }

    @Transactional
    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public List<Transaction> getTransactionsByAccountNumber(String accountNumber) {
        return transactionRepository.findByAccount_AccountNumberOrderByDateDesc(accountNumber);
    }

    public Transaction credit(String accountNumber, double amount) {
        try {
            Account account = accountRepository.findById(accountNumber)
                    .orElseThrow(() -> new IllegalArgumentException("Account not found with account number: " + accountNumber));
            DepositTransaction transaction = new DepositTransaction(amount);
            transaction.setAccount(account);
            account.post(transaction);
            accountRepository.save(account);
            return transaction;
        } catch (IllegalArgumentException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new RuntimeException("Unexpected error while processing credit transaction", ex);
        }
    }

    public Transaction debit(String accountNumber, double amount) {
        try {
            Account account = accountRepository.findById(accountNumber)
                    .orElseThrow(() -> new IllegalArgumentException("Account not found with account number: " + accountNumber));
        WithdrawalTransaction transaction = new WithdrawalTransaction(amount);
        transaction.setAccount(account);
        account.post(transaction);
        accountRepository.save(account);
        return transaction;
        } catch (IllegalArgumentException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new RuntimeException("Unexpected error while processing credit transaction", ex);
        }
    }

    public Transaction billPayment(String accountNumber, double amount, BillTypes billType, String reference) {
        try {
            Account account = accountRepository.findById(accountNumber)
                    .orElseThrow(() -> new IllegalArgumentException("Account not found with account number: " + accountNumber));
        BillPaymentTransaction transaction = new BillPaymentTransaction(amount, billType, reference);
        transaction.setAccount(account);
        account.post(transaction);
        accountRepository.save(account);
        return transaction;
        } catch (IllegalArgumentException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new RuntimeException("Unexpected error while processing credit transaction", ex);
        }
    }
}
