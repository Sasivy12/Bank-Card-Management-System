package com.example.bank_card_management.exception;

public class InsufficientCardBalanceException extends RuntimeException
{
    public InsufficientCardBalanceException(String message)
    {
        super(message);
    }
}
