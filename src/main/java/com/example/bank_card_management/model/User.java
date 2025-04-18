package com.example.bank_card_management.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "user_")
@Data
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    private String fullName;

    private String role;

    private String status;

    @OneToMany(mappedBy = "cardHolder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BankCard> cards;

}
