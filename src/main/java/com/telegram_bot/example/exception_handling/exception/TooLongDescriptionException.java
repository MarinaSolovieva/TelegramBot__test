package com.telegram_bot.example.exception_handling.exception;

public class TooLongDescriptionException extends RuntimeException {
    public TooLongDescriptionException(String message) {
        super(message);
    }
}
