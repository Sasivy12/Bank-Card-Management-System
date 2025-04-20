package com.example.bank_card_management.service;

import com.example.bank_card_management.dto.CreateBankCardRequest;
import com.example.bank_card_management.exception.FailedBankCardEncryptionException;
import com.example.bank_card_management.model.BankCard;
import com.example.bank_card_management.model.CardStatus;
import com.example.bank_card_management.model.User;
import com.example.bank_card_management.repository.BankCardRepository;
import com.example.bank_card_management.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class BankCardService
{
    private final BankCardRepository bankCardRepository;

    private final EncryptionService encryptionService;

    private final UserRepository userRepository;

    public BankCard createBankCard(CreateBankCardRequest request)
    {
        try
        {
            String encrypted = encryptionService.encrypt(request.getEncryptedCardNumber());
            User user = userRepository.findById(request.getCardHolderId()).orElseThrow(
                    () -> new RuntimeException("User not found"));

            BankCard bankCard = new BankCard();
            bankCard.setCardHolder(user);
            bankCard.setExpiryDate(request.getExpiryDate());
            bankCard.setEncryptedCardNumber(encrypted);
            bankCard.setCardStatus(CardStatus.ACTIVE);
            bankCard.setBalance(BigDecimal.valueOf(0.0));

            return bankCardRepository.save(bankCard);
        }
        catch (Exception e)
        {
            throw new FailedBankCardEncryptionException("Failed to encrypt card number");
        }
    }

    private String maskCardNumber(String cardNumber)
    {
        return "**** **** **** " + cardNumber.substring(cardNumber.length() - 4);
    }
}
