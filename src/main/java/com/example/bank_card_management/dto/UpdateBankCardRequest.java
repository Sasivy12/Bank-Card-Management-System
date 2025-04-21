package com.example.bank_card_management.dto;

import com.example.bank_card_management.model.CardStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class UpdateBankCardRequest
{
    private Long cardId;
    private LocalDate expiryDate;
    private CardStatus cardStatus;
    private BigDecimal balance;
}