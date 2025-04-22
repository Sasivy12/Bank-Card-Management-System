package com.example.bank_card_management.controller;

import com.example.bank_card_management.dto.CreateTransactionRequest;
import com.example.bank_card_management.model.Transaction;
import com.example.bank_card_management.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transaction")
@Tag(name = "Управление транзакциями", description = "Создание и получение транзакций")
public class TransactionController
{
    private final TransactionService transactionService;

    @PostMapping()
    @PreAuthorize("#auth.name == @userService.getEmailByCardId(#createTransactionRequest.sourceCard.id)")
    @Operation(summary = "Создать транзакцию")
    @ApiResponses(value =
            {
            @ApiResponse(responseCode = "200", description = "Транзакция успешно создана"),
            @ApiResponse(responseCode = "401", description = "Нет доступа")
            })
    public Transaction createTransaction
            (@RequestBody CreateTransactionRequest createTransactionRequest, Authentication auth)
    {
        return transactionService.createTransaction(createTransactionRequest);
    }

    @GetMapping()
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Получить все транзакции (только для администратора)")
    @ApiResponses(value =
            {
            @ApiResponse(responseCode = "200", description = "Список транзакций успешно получен"),
            @ApiResponse(responseCode = "401", description = "Нет доступа")
            })
    public List<Transaction> getAllTransactions()
    {
        return transactionService.getAllTransactions();
    }
}
