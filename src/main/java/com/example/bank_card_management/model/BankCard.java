package com.example.bank_card_management.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "bankcard")
@Data
public class BankCard
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String encryptedCardNumber;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User cardHolder;

    private LocalDate expiryDate;

    @Enumerated(EnumType.STRING)
    private CardStatus cardStatus;

    private BigDecimal balance;

}
