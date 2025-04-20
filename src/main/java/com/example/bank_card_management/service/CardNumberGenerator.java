package com.example.bank_card_management.service;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class CardNumberGenerator
{
    private final Random random = new Random();

    public String generateCardNumber()
    {
        int[] digits = new int[16];

        for (int i = 0; i < 15; i++)
        {
            digits[i] = random.nextInt(10);
        }

        digits[15] = getLuhnCheckDigit(digits);

        StringBuilder cardNumber = new StringBuilder();
        for (int digit : digits)
        {
            cardNumber.append(digit);
        }

        return cardNumber.toString();
    }

    private int getLuhnCheckDigit(int[] digits)
    {
        int sum = 0;
        for (int i = 0; i < 15; i++) {
            int digit = digits[14 - i];
            if (i % 2 == 0)
            {
                digit *= 2;
                if (digit > 9) digit -= 9;
            }
            sum += digit;
        }
        return (10 - (sum % 10)) % 10;
    }
}