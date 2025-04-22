package com.example.bank_card_management.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "user_")
@Data
@Schema(description = "Пользователь системы")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Уникальный идентификатор пользователя")
    private Long id;

    @Schema(description = "Email пользователя")
    private String email;

    @Schema(description = "Зашифрованный пароль пользователя")
    private String password;

    @Schema(description = "Полное имя пользователя")
    private String fullName;

    @Schema(description = "Роль пользователя")
    private String role;

    @Schema(description = "Статус пользователя")
    private String status;

    @JsonIgnore
    @OneToMany(mappedBy = "cardHolder", cascade = CascadeType.ALL, orphanRemoval = true)
    @Schema(description = "Список банковских карт, принадлежащих пользователю")
    private List<BankCard> cards;

}
