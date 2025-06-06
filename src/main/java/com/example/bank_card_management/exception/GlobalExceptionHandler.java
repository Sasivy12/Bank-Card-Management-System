package com.example.bank_card_management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler
{
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFound(UserNotFoundException ex)
    {
        ErrorResponse errorResponse = new ErrorResponse("USER_NOT_FOUND", ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyExists(UserAlreadyExistsException ex)
    {
        ErrorResponse errorResponse = new ErrorResponse("USER_ALREADY_EXISTS", ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(AuthenticationFailedException.class)
    public ResponseEntity<ErrorResponse> handleAuthenticationFailed(AuthenticationFailedException ex)
    {
        ErrorResponse errorResponse = new ErrorResponse("AUTHENTICATION_FAILED", ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(FailedBankCardEncryptionException.class)
    public ResponseEntity<ErrorResponse> handleFailedBankCardEncryption(FailedBankCardEncryptionException ex)
    {
        ErrorResponse errorResponse = new ErrorResponse("ENCRYPTION_FAILED", ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BankCardNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleBankCardNotFound(BankCardNotFoundException ex)
    {
        ErrorResponse errorResponse = new ErrorResponse("BANKCARD_NOT_FOUND", ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InsufficientCardBalanceException.class)
    public ResponseEntity<ErrorResponse> InsufficientCardBalance(InsufficientCardBalanceException ex)
    {
        ErrorResponse errorResponse = new ErrorResponse("INSUFFICIENT_BALANCE", ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InactiveCardException.class)
    public ResponseEntity<ErrorResponse> handleInactiveCard(InactiveCardException ex)
    {
        ErrorResponse errorResponse = new ErrorResponse("INACTIVE_CARD", ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }
}
