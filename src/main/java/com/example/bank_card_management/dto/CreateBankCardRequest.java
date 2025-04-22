package com.example.bank_card_management.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

@Data
@Schema(description = "Запрос на создание новой банковской карты")
public class CreateBankCardRequest
{
    @Schema(description = "ID владельца карты")
    private Long cardHolderId;

    @Schema(description = "Дата истечения срока действия карты")
    private LocalDate expiryDate;
}
