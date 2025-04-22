package com.example.bank_card_management.controller;

import com.example.bank_card_management.model.User;
import com.example.bank_card_management.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name = "Управление пользователями", description = "Операции для получения, обновления и удаления пользователей")
public class UserController
{
    private final UserService userService;

    @GetMapping()
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Получить список всех пользователей (только для администратора)")
    @ApiResponses(value =
            {
            @ApiResponse(responseCode = "200", description = "Список пользователей успешно получен"),
            @ApiResponse(responseCode = "401", description = "Нет доступа")
            })
    public List<User> getAllUsers()
    {
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    @Operation(summary = "Получить конкретного пользователя по ID")
    @ApiResponses(value =
            {
            @ApiResponse(responseCode = "200", description = "Пользователь найден"),
            @ApiResponse(responseCode = "401", description = "Пользователь не найден")
            })
    public User getASpecificUser(@PathVariable Long userId)
    {
        return userService.getASpecificUser(userId);
    }

    @DeleteMapping()
    @PreAuthorize("hasRole('ADMIN') or #auth.name == @userService.getEmailByCardId(#request.cardId)")
    @Operation(summary = "Удалить пользователя (админ или сам пользователь)")
    @ApiResponses(value =
            {
            @ApiResponse(responseCode = "200", description = "Пользователь удалён"),
            @ApiResponse(responseCode = "401", description = "Нет доступа")
            })
    public ResponseEntity<String> deleteASpecificUser(@RequestBody User user, Authentication auth)
    {
        userService.deleteASpecificUser(user);

        return ResponseEntity.ok("User " + user.getEmail() + " successfully deleted");
    }

    @PutMapping()
    @PreAuthorize("hasRole('ADMIN') or #auth.name == @userService.getEmailByCardId(#request.cardId)")
    @Operation(summary = "Обновить данные пользователя (админ или сам пользователь)")
    @ApiResponses(value =
            {
            @ApiResponse(responseCode = "200", description = "Пользователь обновлён"),
            @ApiResponse(responseCode = "401", description = "Нет доступа / Пользователь не найден")
            })
    public ResponseEntity<String> updateASpecificUser(@RequestBody User user, Authentication auth)
    {
        userService.updateASpecificUser(user);

        return ResponseEntity.ok("User " + user.getEmail() + " successfully updated");
    }
}
