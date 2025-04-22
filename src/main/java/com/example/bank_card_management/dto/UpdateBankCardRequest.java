package com.example.bank_card_management.dto;

import com.example.bank_card_management.model.CardStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Schema(description = "Запрос на обновление данных банковской карты")
public class UpdateBankCardRequest
{
    @Schema(description = "ID карты")
    private Long cardId;

    @Schema(description = "Дата окончания срока действия карты")
    private LocalDate expiryDate;

    @Schema(description = "Статус карты")
    private CardStatus cardStatus;

    @Schema(description = "Баланс карты")
    private BigDecimal balance;
}