package com.example.bank_card_management.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "LoginRequest", description = "Запрос на вход в систему")
public class LoginRequest
{
    @Schema(description = "Email пользователя")
    private String email;

    @Schema(description = "Пароль пользователя")
    private String password;
}
