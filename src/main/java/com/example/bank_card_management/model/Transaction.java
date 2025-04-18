package com.example.bank_card_management.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "transaction")
@Data
public class Transaction
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate timeStamp;

    private String description;

    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "source_card_id")
    private BankCard sourceCard;

    @ManyToOne
    @JoinColumn(name = "destination_card_id")
    private BankCard destinationCard;
}
