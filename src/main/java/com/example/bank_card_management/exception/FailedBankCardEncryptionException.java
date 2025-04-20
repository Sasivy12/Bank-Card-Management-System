package com.example.bank_card_management.exception;

public class FailedBankCardEncryptionException extends RuntimeException
{
    public FailedBankCardEncryptionException(String message)
    {
        super(message);
    }
}
