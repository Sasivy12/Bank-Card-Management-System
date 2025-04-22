package com.example.bank_card_management.controller;

import com.example.bank_card_management.dto.LoginRequest;
import com.example.bank_card_management.model.User;
import com.example.bank_card_management.service.UserAuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping()
@Tag(name = "Аутентификация пользователей", description = "Регистрация и вход в систему")
public class UserAuthController
{
    private final UserAuthService userService;

    @PostMapping("/register")
    @Operation(summary = "Регистрация нового пользователя")
    @ApiResponses(value =
            {
            @ApiResponse(responseCode = "200", description = "Пользователь успешно зарегистрирован"),
            @ApiResponse(responseCode = "401", description = "Ошибка валидации данных"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
            })
    public ResponseEntity<String> register(@RequestBody User user)
    {
        userService.register(user);

        return ResponseEntity.ok("User " + user.getEmail() + " registered successfully");
    }

    @PostMapping("/login")
    @Operation(summary = "Вход пользователя в систему")
    @ApiResponses(value =
            {
            @ApiResponse(responseCode = "200", description = "Успешная аутентификация"),
            @ApiResponse(responseCode = "401", description = "Неверный email или пароль"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
            })
    public String login(@RequestBody LoginRequest loginRequest)
    {
        return userService.verify(loginRequest);
    }
}
