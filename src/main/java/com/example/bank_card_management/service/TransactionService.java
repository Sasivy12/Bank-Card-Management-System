package com.example.bank_card_management.service;

import com.example.bank_card_management.dto.CreateTransactionRequest;
import com.example.bank_card_management.dto.UpdateBankCardRequest;
import com.example.bank_card_management.exception.BankCardNotFoundException;
import com.example.bank_card_management.exception.InactiveCardException;
import com.example.bank_card_management.exception.InsufficientCardBalanceException;
import com.example.bank_card_management.model.BankCard;
import com.example.bank_card_management.model.CardStatus;
import com.example.bank_card_management.model.Transaction;
import com.example.bank_card_management.repository.BankCardRepository;
import com.example.bank_card_management.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService
{
    private final TransactionRepository transactionRepository;

    private final BankCardRepository bankCardRepository;

    private final BankCardService bankCardService;

    public Transaction createTransaction(CreateTransactionRequest request)
    {
        BankCard sourceCard = bankCardRepository.findById(request.getSourceCard().getId())
                .orElseThrow(() -> new BankCardNotFoundException("Source card not found"));

        BankCard destinationCard = bankCardRepository.findById(request.getDestinationCard().getId())
                .orElseThrow(() -> new BankCardNotFoundException("Destination card not found"));

        BigDecimal amount = request.getAmount();

        if (!CardStatus.ACTIVE.equals(sourceCard.getCardStatus()))
        {
            throw new InactiveCardException("Source card is not active");
        }

        if (!CardStatus.ACTIVE.equals(destinationCard.getCardStatus()))
        {
            throw new InactiveCardException("Destination card is not active");
        }

        if (sourceCard.getBalance().compareTo(amount) < 0)
        {
            throw new InsufficientCardBalanceException("Insufficient funds");
        }

        sourceCard.setBalance(sourceCard.getBalance().subtract(amount));
        destinationCard.setBalance(destinationCard.getBalance().add(amount));

        Transaction transaction = new Transaction();
        transaction.setSourceCard(sourceCard);
        transaction.setDestinationCard(destinationCard);
        transaction.setAmount(amount);
        transaction.setDescription(request.getDescription());
        transaction.setTimeStamp(LocalDate.from(LocalDateTime.now()));

        sourceCard.getOutgoingTransactions().add(transaction);
        destinationCard.getIncomingTransactions().add(transaction);

        return transactionRepository.save(transaction);
    }
}
