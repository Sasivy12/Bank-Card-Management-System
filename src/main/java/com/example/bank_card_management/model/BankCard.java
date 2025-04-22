package com.example.bank_card_management.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    @JsonIgnoreProperties("cards")
    private User cardHolder;

    private LocalDate expiryDate;

    @Enumerated(EnumType.STRING)
    private CardStatus cardStatus;

    private BigDecimal balance;

    @OneToMany(mappedBy = "sourceCard", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Transaction> outgoingTransactions = new ArrayList<>();

    @OneToMany(mappedBy = "destinationCard", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Transaction> incomingTransactions = new ArrayList<>();

}
