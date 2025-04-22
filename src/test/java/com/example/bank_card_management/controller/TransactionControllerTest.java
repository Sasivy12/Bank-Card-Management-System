package com.example.bank_card_management.controller;

import com.example.bank_card_management.dto.CreateTransactionRequest;
import com.example.bank_card_management.model.BankCard;
import com.example.bank_card_management.model.Transaction;
import com.example.bank_card_management.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.security.core.Authentication;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransactionControllerTest {

    @Mock
    private TransactionService transactionService;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private TransactionController transactionController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateTransaction() {
        CreateTransactionRequest request = new CreateTransactionRequest();
        BankCard sourceCard = new BankCard();
        sourceCard.setId(1L);
        request.setSourceCard(sourceCard);

        Transaction expectedTransaction = new Transaction();
        when(transactionService.createTransaction(request)).thenReturn(expectedTransaction);

        Transaction result = transactionController.createTransaction(request, authentication);

        assertEquals(expectedTransaction, result);
        verify(transactionService).createTransaction(request);
    }

    @Test
    void testGetAllTransactions() {
        Transaction tx1 = new Transaction();
        Transaction tx2 = new Transaction();

        when(transactionService.getAllTransactions()).thenReturn(Arrays.asList(tx1, tx2));

        List<Transaction> result = transactionController.getAllTransactions();

        assertEquals(2, result.size());
        verify(transactionService).getAllTransactions();
    }
}
