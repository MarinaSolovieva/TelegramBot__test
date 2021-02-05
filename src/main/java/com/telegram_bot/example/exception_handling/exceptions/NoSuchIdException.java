package com.telegram_bot.example.exception_handling.exceptions;

public class NoSuchIdException extends RuntimeException{
    public NoSuchIdException(String message) {
        super(message);
    }
}
