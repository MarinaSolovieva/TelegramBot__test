package com.telegram_bot.example.service;

import com.telegram_bot.example.model.dto.CityResponseDTO;
import com.telegram_bot.example.model.entity.City;

public interface CityService {

    public CityResponseDTO findByName(String name);

    public void deleteById(int id);

    public CityResponseDTO save(City city);

    public CityResponseDTO update(City city);
}
