package com.example.bank_card_management.service;

import com.example.bank_card_management.dto.CreateBankCardRequest;
import com.example.bank_card_management.exception.FailedBankCardEncryptionException;
import com.example.bank_card_management.exception.UserNotFoundException;
import com.example.bank_card_management.model.BankCard;
import com.example.bank_card_management.model.CardStatus;
import com.example.bank_card_management.model.User;
import com.example.bank_card_management.repository.BankCardRepository;
import com.example.bank_card_management.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BankCardService
{
    private final BankCardRepository bankCardRepository;

    private final EncryptionService encryptionService;

    private final UserRepository userRepository;

    private final CardNumberGenerator cardNumberGenerator;

    public BankCard createBankCard(CreateBankCardRequest request)
    {
        try
        {
            String rawCardNumber = cardNumberGenerator.generateCardNumber();
            System.out.println(rawCardNumber);
            String encrypted = encryptionService.encrypt(rawCardNumber);

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

    public List<BankCard> getAllBankCards()
    {
        return bankCardRepository.findAll().stream().map(card ->
        {
            try
            {
                String decrypted = encryptionService.decrypt(card.getEncryptedCardNumber());
                card.setEncryptedCardNumber("**** **** **** " + decrypted.substring(decrypted.length() - 4));
            }
            catch (Exception e)
            {
                card.setEncryptedCardNumber("**** **** **** ****");
            }
            return card;
        }).collect(Collectors.toList());
    }

    public List<BankCard> getCardsForCurrentUser(Authentication authentication)
    {
        String userEmail = authentication.getName();

        User user = userRepository.findByEmail(userEmail).orElseThrow(
                () -> new UserNotFoundException("User with email: " + userEmail + " not found"));

        return user.getCards().stream().map(card ->
        {
            try
            {
                String decrypted = encryptionService.decrypt(card.getEncryptedCardNumber());
                card.setEncryptedCardNumber("**** **** **** " + decrypted.substring(decrypted.length() - 4));
            }
            catch (Exception e)
            {
                card.setEncryptedCardNumber("**** **** **** ****");
            }
            return card;
        }).collect(Collectors.toList());
    }
}
