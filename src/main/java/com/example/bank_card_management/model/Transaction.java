package com.example.bank_card_management.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "transaction")
@Data
@Schema(title = "Transaction",description = "Транзакция между банковскими картами")
public class Transaction
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(description = "Уникальный идентификатор транзакции")
    private Long id;

    @Schema(description = "Дата и время транзакции")
    private LocalDate timeStamp;

    @Schema(description = "Описание транзакции")
    private String description;

    @Schema(description = "Сумма транзакции")
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "source_card_id")
    @Schema(description = "Исходная карта (отправитель)")
    private BankCard sourceCard;

    @ManyToOne
    @JoinColumn(name = "destination_card_id")
    @Schema(description = "Целевая карта (получатель)")
    private BankCard destinationCard;
}
