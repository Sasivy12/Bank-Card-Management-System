package com.example.bank_card_management.controller;

import com.example.bank_card_management.dto.CreateBankCardRequest;
import com.example.bank_card_management.dto.DeleteBankCardRequest;
import com.example.bank_card_management.dto.UpdateBankCardRequest;
import com.example.bank_card_management.model.BankCard;
import com.example.bank_card_management.service.BankCardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BankCardControllerTest {

    @Mock
    private BankCardService bankCardService;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private BankCardController bankCardController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateBankCard() {
        CreateBankCardRequest request = new CreateBankCardRequest();
        request.setCardHolderId(1L);

        ResponseEntity<String> response = bankCardController.createBankCard(request);

        assertEquals("Card successfully created for user: 1", response.getBody());
        verify(bankCardService).createBankCard(request);
    }

    @Test
    void testGetAllBankCards() {
        BankCard card1 = new BankCard();
        BankCard card2 = new BankCard();

        when(bankCardService.getAllBankCards()).thenReturn(Arrays.asList(card1, card2));

        List<BankCard> result = bankCardController.getAllBankCards();

        assertEquals(2, result.size());
        verify(bankCardService).getAllBankCards();
    }

    @Test
    void testGetBankCardsForCurrentUser() {
        BankCard card = new BankCard();

        when(bankCardService.getCardsForCurrentUser(authentication)).thenReturn(List.of(card));

        List<BankCard> result = bankCardController.getBankCardsForCurrentUser(authentication);

        assertEquals(1, result.size());
        verify(bankCardService).getCardsForCurrentUser(authentication);
    }

    @Test
    void testDeleteASpecificBankCard() {
        DeleteBankCardRequest request = new DeleteBankCardRequest();
        request.setCardId(42L);

        ResponseEntity<String> response = bankCardController.deleteASpecificBankCard(request, authentication);

        assertEquals("Card with ID 42 deleted successfully", response.getBody());
        verify(bankCardService).deleteACard(request);
    }

    @Test
    void testUpdateBankCard() {
        UpdateBankCardRequest request = new UpdateBankCardRequest();
        request.setCardId(123L);

        ResponseEntity<String> response = bankCardController.updateBankCard(request, authentication);

        assertEquals("Card with ID 123 updated successfully", response.getBody());
        verify(bankCardService).updateBankCard(request);
    }
}
