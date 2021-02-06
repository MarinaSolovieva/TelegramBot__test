package com.telegram_bot.example.service;

import com.telegram_bot.example.dao.CityRepository;
import com.telegram_bot.example.exception_handling.exception.EqualCityNameException;
import com.telegram_bot.example.exception_handling.exception.NoSuchCityException;
import com.telegram_bot.example.exception_handling.exception.NoSuchIdException;
import com.telegram_bot.example.exception_handling.exception.TooLongDescriptionException;
import com.telegram_bot.example.model.dto.CityResponseDTO;
import com.telegram_bot.example.model.entity.City;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl implements CityService {

    private static final Logger logger = LogManager.getLogger(CityServiceImpl.class);

    private final CityRepository cityRepository;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public CityResponseDTO findByName(String name) {
        logger.info("Start method findByName with name = {}", name);
        City city = cityRepository.findCityByName(name);
        if (city == null) {
            logger.info("Нет города с таким названием = {}", name);
            throw new NoSuchCityException("Города с таким названием в базе нет");
        }
        return toCityResponseDTO(city);
    }

    @Override
    public void deleteById(long id) {
        logger.info("Start method deleteById with id = {}", id);
        if (!cityRepository.existsById(id)) {
            logger.info("Невозможно удалить город с id, которого не существует = {}", id);
            throw new NoSuchIdException("Города с  id = " + id + " не существует!");
        }
        cityRepository.deleteById(id);
    }

    @Override
    public CityResponseDTO save(City city) {
        logger.info("Start method save with name  = {}", city.getName());
        if (cityRepository.existsByName(city.getName())) {
            logger.info("Город с таким именем уже существует = {}", city.getName());
            throw new EqualCityNameException("Город с названием " + city.getName() + " уже существует!");
        }
        if (city.getDescription().length() > 100) {
            logger.info("Длина описания не может быть больше 100 символов = {}", city.getDescription().length());
            throw new TooLongDescriptionException("Поле description может содержать не более 100 символов");
        }
        return toCityResponseDTO(cityRepository.save(city));
    }

    @Override
    public CityResponseDTO update(City city) {
        logger.info("Start method update with name  = {}", city.getName());
        if (city.getDescription().length() > 100) {
            logger.info("Длина описания не может быть больше 100 символов = {}", city.getDescription().length());
            throw new TooLongDescriptionException("Поле description может содержать не более 100 символов");
        }
        return toCityResponseDTO(cityRepository.save(city));
    }

    private CityResponseDTO toCityResponseDTO(City city) {
        return new CityResponseDTO(city.getId(), city.getName(), city.getDescription());
    }
}
