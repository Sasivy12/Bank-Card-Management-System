package com.example.bank_card_management.dto;

import com.example.bank_card_management.model.BankCard;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateTransactionRequest
{
    private String description;

    private BigDecimal amount;

    private BankCard sourceCard;

    private BankCard destinationCard;

}
