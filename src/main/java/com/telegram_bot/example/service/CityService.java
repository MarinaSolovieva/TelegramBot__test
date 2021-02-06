package com.telegram_bot.example.service;

import com.telegram_bot.example.model.dto.CityResponseDTO;
import com.telegram_bot.example.model.entity.City;

public interface CityService {

    CityResponseDTO findByName(String name);

    void deleteById(long id);

    CityResponseDTO save(City city);

    CityResponseDTO update(City city);
}
