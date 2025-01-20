package com.eteration.simplebanking.services;

import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.DepositTransaction;
import com.eteration.simplebanking.model.Transaction;
import com.eteration.simplebanking.model.WithdrawalTransaction;
import com.eteration.simplebanking.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account getAccount(String accountNumber) {
        try {
            return accountRepository.findById(accountNumber)
                    .orElseThrow(() -> new IllegalArgumentException("Account not found with account number: " + accountNumber));
        } catch (IllegalArgumentException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new RuntimeException("Unexpected error while fetching account", ex);
        }
    }


    public Account createAccount(Account account) {
        if (accountRepository.existsById(account.getAccountNumber())) {
            throw new IllegalArgumentException("Account already exists with account number: " + account.getAccountNumber());
        }
        try {
            return accountRepository.save(account);
        } catch (Exception ex) {
            throw new RuntimeException("Error while creating account", ex);
        }

    }
}
