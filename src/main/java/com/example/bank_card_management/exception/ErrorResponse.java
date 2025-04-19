package com.example.bank_card_management.exception;

public class ErrorResponse
{
    private String errorCode;
    private String message;

    public ErrorResponse(String errorCode, String message)
    {
        this.errorCode = errorCode;
        this.message = message;
    }
}
