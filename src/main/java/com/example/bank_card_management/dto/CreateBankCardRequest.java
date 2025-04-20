package com.example.bank_card_management.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateBankCardRequest
{
    private String encryptedCardNumber;

    private Long cardHolderId;

    private LocalDate expiryDate;
}
