package com.eteration.simplebanking.controller.interfaces;

import com.eteration.simplebanking.dto.AccountDTO;
import com.eteration.simplebanking.model.Account;
import org.springframework.http.ResponseEntity;

public interface AccountControllerOperations {
    ResponseEntity<AccountDTO> getAccountDetails(String accountNumber);
    ResponseEntity<AccountDTO> createAccount(Account accountDTO);
}