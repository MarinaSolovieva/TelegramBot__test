package com.telegram_bot.example.exception_handling.exceptions;

public class TooLongDescriptionException  extends RuntimeException{
    public TooLongDescriptionException(String message) {
        super(message);
    }
}
