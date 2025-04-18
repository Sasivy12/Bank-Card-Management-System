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

    private String cardNumber;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User cardHolder;

    private LocalDate expiryDate;

    private String cardStatus;

    private BigDecimal balance;

}
