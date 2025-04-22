package com.example.bank_card_management.controller;

import com.example.bank_card_management.dto.CreateBankCardRequest;
import com.example.bank_card_management.dto.DeleteBankCardRequest;
import com.example.bank_card_management.dto.UpdateBankCardRequest;
import com.example.bank_card_management.model.BankCard;
import com.example.bank_card_management.service.BankCardService;
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
@RequiredArgsConstructor
@RequestMapping("/card")
@Tag(name = "Управление банковскими картами", description = "Операции по созданию, получению, обновлению и удалению карт")
public class BankCardController
{
    private final BankCardService bankCardService;

    @PostMapping()
    @Operation(summary = "Создать банковскую карту")
    @ApiResponses(value =
            {
            @ApiResponse(responseCode = "200", description = "Карта успешно создана")
            })
    public ResponseEntity<String> createBankCard(@RequestBody CreateBankCardRequest request)
    {
        bankCardService.createBankCard(request);

        return ResponseEntity.ok("Card successfully created for user: " + request.getCardHolderId());
    }

    @GetMapping()
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Получить все карты (только для администратора)")
    @ApiResponses(value =
            {
            @ApiResponse(responseCode = "200", description = "Список карт успешно получен"),
            @ApiResponse(responseCode = "401", description = "Нет доступа")
            })
    public List<BankCard> getAllBankCards()
    {
        return bankCardService.getAllBankCards();
    }

    @GetMapping("/my")
    @Operation(summary = "Получить карты текущего пользователя")
    @ApiResponses(value =
            {
            @ApiResponse(responseCode = "200", description = "Карты пользователя успешно получены")
            })
    public List<BankCard> getBankCardsForCurrentUser(Authentication authentication)
    {
        return bankCardService.getCardsForCurrentUser(authentication);
    }

    @DeleteMapping()
    @PreAuthorize("hasRole('ADMIN') or #auth.name == @userService.getEmailByCardId(#user.id)")
    @Operation(summary = "Удалить карту (админ или владелец)")
    @ApiResponses(value =
            {
            @ApiResponse(responseCode = "200", description = "Карта удалена"),
            @ApiResponse(responseCode = "401", description = "Нет доступа")
            })
    public ResponseEntity<String> deleteASpecificBankCard
            (@RequestBody DeleteBankCardRequest request, Authentication auth)
    {
        bankCardService.deleteACard(request);
        return ResponseEntity.ok("Card with ID " + request.getCardId() + " deleted successfully");
    }

    @PutMapping()
    @PreAuthorize("hasRole('ADMIN') or #auth.name == @userService.getEmailByCardId(#request.cardId)")
    @Operation(summary = "Обновить карту (админ или владелец)")
    @ApiResponses(value =
            {
            @ApiResponse(responseCode = "200", description = "Карта обновлена"),
            @ApiResponse(responseCode = "401", description = "Нет доступа")
            })
    public ResponseEntity<String> updateBankCard
            (@RequestBody UpdateBankCardRequest request, Authentication auth)
    {
        bankCardService.updateBankCard(request);
        return ResponseEntity.ok("Card with ID " + request.getCardId() + " updated successfully");
    }
}
