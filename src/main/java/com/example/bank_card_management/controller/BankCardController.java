package com.example.bank_card_management.controller;

import com.example.bank_card_management.dto.CreateBankCardRequest;
import com.example.bank_card_management.dto.DeleteBankCardRequest;
import com.example.bank_card_management.model.BankCard;
import com.example.bank_card_management.service.BankCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/card")
public class BankCardController
{
    private final BankCardService bankCardService;

    @PostMapping()
    public ResponseEntity<String> createBankCard(@RequestBody CreateBankCardRequest request)
    {
        bankCardService.createBankCard(request);

        return ResponseEntity.ok("Card successfully created for user: " + request.getCardHolderId());
    }

    @GetMapping()
    public List<BankCard> getAllBankCards()
    {
        return bankCardService.getAllBankCards();
    }

    @GetMapping("/my")
    public List<BankCard> getBankCardsForCurrentUser(Authentication authentication)
    {
        return bankCardService.getCardsForCurrentUser(authentication);
    }

    @DeleteMapping()
    public void deleteASpecificBankCard(@RequestBody DeleteBankCardRequest request)
    {
        bankCardService.deleteACard(request);
    }
}
