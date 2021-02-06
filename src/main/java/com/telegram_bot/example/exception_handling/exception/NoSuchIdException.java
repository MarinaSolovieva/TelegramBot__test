package com.telegram_bot.example.exception_handling.exception;

public class NoSuchIdException extends RuntimeException {
    public NoSuchIdException(String message) {
        super(message);
    }
}
