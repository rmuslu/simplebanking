package com.eteration.simplebanking.controller;


import com.eteration.simplebanking.dto.TransactionDTO;
import com.eteration.simplebanking.model.TransactionStatus;
import com.eteration.simplebanking.services.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account/v1")
public class TransactionController {
    private TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }
    /**
     * Hesaba para yatırır.
     * POST /api/account/v1/credit/{accountNumber}
     */
    @PostMapping("/credit/{accountNumber}")
    public ResponseEntity<TransactionDTO> credit(@PathVariable String accountNumber, @RequestBody TransactionDTO transactionDTO) {
        TransactionDTO transaction = new TransactionDTO(transactionService.credit(accountNumber, transactionDTO.getAmount()));
        return ResponseEntity.ok(transaction);
    }

    /**
     * Hesaptan para çeker.
     * POST /api/account/v1/debit/{accountNumber}
     */
    @PostMapping("/debit/{accountNumber}")
    public ResponseEntity<TransactionDTO> debit(@PathVariable String accountNumber, @RequestBody TransactionDTO transactionDTO) {
        TransactionDTO transaction = new TransactionDTO(transactionService.debit(accountNumber, transactionDTO.getAmount()));
        return ResponseEntity.ok(transaction);
    }
    @PostMapping("/bill-payment/{accountNumber}")
    ResponseEntity<TransactionStatus> billPayment(
            @PathVariable String accountNumber,
            @RequestBody TransactionDTO transactionDTO) {
        transactionService.billPayment(accountNumber, transactionDTO.getAmount(),
                transactionDTO.getBillType(), transactionDTO.getReference());
        return ResponseEntity.ok(new TransactionStatus("OK"));
    }

}
