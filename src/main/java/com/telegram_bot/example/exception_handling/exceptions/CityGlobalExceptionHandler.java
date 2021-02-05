package com.telegram_bot.example.exception_handling.exceptions;

import com.telegram_bot.example.service.CityServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CityGlobalExceptionHandler {

    private static final Logger logger = LogManager.getLogger(CityGlobalExceptionHandler.class);

    @ExceptionHandler
    public ResponseEntity<CityIncorrectData> handleException(NoSuchCityException exception) {
        logger.error(exception);
        CityIncorrectData cityIncorrectData = new CityIncorrectData();
        cityIncorrectData.setInfo(exception.getMessage());
        return new ResponseEntity<> (cityIncorrectData, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<CityIncorrectData> handleException(Exception exception) {
        CityIncorrectData cityIncorrectData = new CityIncorrectData();
        cityIncorrectData.setInfo(exception.getMessage());
        return new ResponseEntity<> (cityIncorrectData, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<CityIncorrectData> handleException(TooLongDescriptionException exception) {
        CityIncorrectData cityIncorrectData = new CityIncorrectData();
        cityIncorrectData.setInfo(exception.getMessage());
        return new ResponseEntity<> (cityIncorrectData, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<CityIncorrectData> handleException(EqualCityNameException exception) {
        CityIncorrectData cityIncorrectData = new CityIncorrectData();
        cityIncorrectData.setInfo(exception.getMessage());
        return new ResponseEntity<> (cityIncorrectData, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<CityIncorrectData> handleException(NoSuchIdException exception) {
        CityIncorrectData cityIncorrectData = new CityIncorrectData();
        cityIncorrectData.setInfo(exception.getMessage());
        return new ResponseEntity<> (cityIncorrectData, HttpStatus.NOT_FOUND);
    }
}
