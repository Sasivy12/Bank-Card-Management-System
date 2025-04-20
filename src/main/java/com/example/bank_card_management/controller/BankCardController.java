package com.example.bank_card_management.controller;

import com.example.bank_card_management.dto.CreateBankCardRequest;
import com.example.bank_card_management.service.BankCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
