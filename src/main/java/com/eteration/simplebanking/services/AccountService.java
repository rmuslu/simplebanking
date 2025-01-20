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
        Optional<Account> optionalAccount = accountRepository.findById(accountNumber);
        if (!optionalAccount.isPresent()) {
            throw new IllegalArgumentException("Account not found");
        }
        return optionalAccount.get();
    }




    public Account createAccount(Account account) {
        if (accountRepository.existsById(account.getAccountNumber())) {
            throw new IllegalArgumentException("Account already exists with account number: " + account.getAccountNumber());
        }
        return accountRepository.save(account);
    }
}
