package com.example.bank_card_management.controller;

import com.example.bank_card_management.dto.CreateTransactionRequest;
import com.example.bank_card_management.model.Transaction;
import com.example.bank_card_management.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transaction")
public class TransactionController
{
    private final TransactionService transactionService;

    @PostMapping()
    @PreAuthorize("#auth.name == @userService.getEmailByCardId(#createTransactionRequest.sourceCard.id)")
    public Transaction createTransaction
            (@RequestBody CreateTransactionRequest createTransactionRequest, Authentication auth)
    {
        return transactionService.createTransaction(createTransactionRequest);
    }

    @GetMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public List<Transaction> getAllTransactions()
    {
        return transactionService.getAllTransactions();
    }
}
