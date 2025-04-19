package com.example.bank_card_management.exception;

public class AuthenticationFailedException extends RuntimeException
{
    public AuthenticationFailedException(String message)
    {
        super(message);
    }
}
