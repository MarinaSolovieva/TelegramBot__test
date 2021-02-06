package com.telegram_bot.example.exception_handling;

import com.telegram_bot.example.exception_handling.exception.EqualCityNameException;
import com.telegram_bot.example.exception_handling.exception.NoSuchCityException;
import com.telegram_bot.example.exception_handling.exception.NoSuchIdException;
import com.telegram_bot.example.exception_handling.exception.TooLongDescriptionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CityGlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<CityIncorrectData> handleException(NoSuchCityException exception) {
        CityIncorrectData cityIncorrectData = new CityIncorrectData();
        cityIncorrectData.setInfo(exception.getMessage());
        return new ResponseEntity<>(cityIncorrectData, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<CityIncorrectData> handleException(Exception exception) {
        CityIncorrectData cityIncorrectData = new CityIncorrectData();
        cityIncorrectData.setInfo(exception.getMessage());
        return new ResponseEntity<>(cityIncorrectData, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<CityIncorrectData> handleException(TooLongDescriptionException exception) {
        CityIncorrectData cityIncorrectData = new CityIncorrectData();
        cityIncorrectData.setInfo(exception.getMessage());
        return new ResponseEntity<>(cityIncorrectData, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<CityIncorrectData> handleException(EqualCityNameException exception) {
        CityIncorrectData cityIncorrectData = new CityIncorrectData();
        cityIncorrectData.setInfo(exception.getMessage());
        return new ResponseEntity<>(cityIncorrectData, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<CityIncorrectData> handleException(NoSuchIdException exception) {
        CityIncorrectData cityIncorrectData = new CityIncorrectData();
        cityIncorrectData.setInfo(exception.getMessage());
        return new ResponseEntity<>(cityIncorrectData, HttpStatus.NOT_FOUND);
    }
}
