package com.telegram_bot.example.exception_handling.exceptions;

public class NoSuchCityException extends RuntimeException {
    public NoSuchCityException(String message) {
        super(message);
    }
}
