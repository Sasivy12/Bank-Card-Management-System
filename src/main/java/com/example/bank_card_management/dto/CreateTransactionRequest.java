package com.example.bank_card_management.dto;

import com.example.bank_card_management.model.BankCard;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Schema(description = "Запрос на создание транзакции между банковскими картами")
public class CreateTransactionRequest
{
    @Schema(description = "Описание транзакции")
    private String description;

    @Schema(description = "Сумма транзакции")
    private BigDecimal amount;

    @Schema(description = "Исходная карта (отправитель)")
    private BankCard sourceCard;

    @Schema(description = "Целевая карта (получатель)")
    private BankCard destinationCard;

}
