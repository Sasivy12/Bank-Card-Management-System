package com.example.bank_card_management.exception;

public class InactiveCardException extends RuntimeException
{
    public InactiveCardException(String message)
    {
        super(message);
    }
}
