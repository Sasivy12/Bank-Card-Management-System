package com.example.bank_card_management.exception;

public class BankCardNotFoundException extends RuntimeException
{
    public BankCardNotFoundException(String message)
    {
        super(message);
    }
}
