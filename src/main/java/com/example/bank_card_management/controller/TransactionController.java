package com.example.bank_card_management.controller;

import com.example.bank_card_management.dto.CreateTransactionRequest;
import com.example.bank_card_management.model.Transaction;
import com.example.bank_card_management.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transaction")
public class TransactionController
{
    private final TransactionService transactionService;

    @PostMapping()
    public Transaction createTransaction(@RequestBody CreateTransactionRequest createTransactionRequest)
    {
        return transactionService.createTransaction(createTransactionRequest);
    }
}
