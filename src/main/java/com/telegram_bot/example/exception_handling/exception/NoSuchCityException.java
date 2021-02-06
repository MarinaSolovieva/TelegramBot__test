package com.telegram_bot.example.exception_handling.exception;

public class NoSuchCityException extends RuntimeException {
    public NoSuchCityException(String message) {
        super(message);
    }
}
