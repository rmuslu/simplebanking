package com.eteration.simplebanking.controller;

import com.eteration.simplebanking.controller.interfaces.AccountControllerOperations;
import com.eteration.simplebanking.dto.AccountDTO;
import com.eteration.simplebanking.mapper.Mapper;
import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.services.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account/v1")
public class AccountController implements AccountControllerOperations {

    private final AccountService accountService;
    private final Mapper mapper;

    public AccountController(AccountService accountService, Mapper mapper) {
        this.accountService = accountService;
        this.mapper = mapper;
    }

    /**
     * Bir hesap detaylarını getirir.
     * GET /api/account/v1/{accountNumber}
     */
    @GetMapping("/{accountNumber}")
    public ResponseEntity<AccountDTO> getAccountDetails(@PathVariable String accountNumber) {
        AccountDTO accountDTO = mapper.toDTO(accountService.getAccount(accountNumber));
        return ResponseEntity.ok(accountDTO);
    }

    @PostMapping("/create")
    public ResponseEntity<AccountDTO> createAccount(@RequestBody Account account) {
        AccountDTO createdAccount = mapper.toDTO(accountService.createAccount(account));
        return ResponseEntity.ok(createdAccount);
    }

}
