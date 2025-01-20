package com.eteration.simplebanking.controller.interfaces;

import com.eteration.simplebanking.dto.TransactionDTO;
import com.eteration.simplebanking.model.TransactionStatus;
import org.springframework.http.ResponseEntity;

public interface TransactionControllerOperations {
    ResponseEntity<TransactionDTO> credit(String accountNumber, TransactionDTO transactionDTO);
    ResponseEntity<TransactionDTO> debit(String accountNumber, TransactionDTO transactionDTO);
    ResponseEntity<TransactionStatus> billPayment(String accountNumber, TransactionDTO transactionDTO);
}
