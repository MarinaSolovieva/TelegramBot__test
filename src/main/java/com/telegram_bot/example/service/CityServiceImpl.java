package com.telegram_bot.example.service;

import com.telegram_bot.example.dao.CityRepository;
import com.telegram_bot.example.exception_handling.exceptions.EqualCityNameException;
import com.telegram_bot.example.exception_handling.exceptions.NoSuchCityException;
import com.telegram_bot.example.exception_handling.exceptions.NoSuchIdException;
import com.telegram_bot.example.exception_handling.exceptions.TooLongDescriptionException;
import com.telegram_bot.example.model.dto.CityResponseDTO;
import com.telegram_bot.example.model.entity.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;

    @Override
    public CityResponseDTO findByName(String name) {
        City city = cityRepository.findCityByName(name);
        if (city == null) {
            throw new NoSuchCityException("Города с таким названием в базе нет");
        }
        return toCityResponseDTO(city);
    }

    @Override
    public void deleteById(int id) {
        if (!cityRepository.existsById(id)) {
            throw new NoSuchIdException("Города с  id = " + id + " не существует!");
        }
        cityRepository.deleteById(id);
    }

    @Override
    public CityResponseDTO save(City city) {
        if (cityRepository.existsByName(city.getName())) {
            throw new EqualCityNameException("Город с названием " + city.getName() + " уже существует!");
        }
        if (city.getDescription().length() > 100) {
            throw new TooLongDescriptionException("Поле description может содержать не более 100 символов");
        }
        return toCityResponseDTO(cityRepository.save(city));
    }

    @Override
    public CityResponseDTO update(City city) {
        if (city.getDescription().length() > 100) {
            throw new TooLongDescriptionException("Поле description может содержать не более 100 символов");
        }
        return toCityResponseDTO(cityRepository.save(city));
    }

    private CityResponseDTO toCityResponseDTO(City city) {
        return new CityResponseDTO(city.getId(), city.getName(), city.getDescription());
    }
}
