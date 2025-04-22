package com.example.bank_card_management.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bankcard")
@Data
@Schema(title = "BankCard",description = "Банковская карта пользователя")
public class BankCard
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(description = "Уникальный идентификатор карты")
    private Long id;

    @Schema(description = "Зашифрованный номер карты")
    private String encryptedCardNumber;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnoreProperties("cards")
    @Schema(description = "Владелец карты")
    private User cardHolder;

    @Schema(description = "Дата окончания срока действия карты")
    private LocalDate expiryDate;

    @Enumerated(EnumType.STRING)
    @Schema(description = "Статус карты")
    private CardStatus cardStatus;

    @Schema(description = "Баланс карты")
    private BigDecimal balance;

    @OneToMany(mappedBy = "sourceCard", cascade = CascadeType.ALL)
    @JsonIgnore
    @Schema(description = "Список исходящих транзакций")
    private List<Transaction> outgoingTransactions = new ArrayList<>();

    @OneToMany(mappedBy = "destinationCard", cascade = CascadeType.ALL)
    @JsonIgnore
    @Schema(description = "Список входящих транзакций")
    private List<Transaction> incomingTransactions = new ArrayList<>();

}
