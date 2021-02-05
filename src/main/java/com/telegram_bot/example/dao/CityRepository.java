package com.telegram_bot.example.dao;

import com.telegram_bot.example.model.entity.City;
import org.springframework.data.repository.CrudRepository;

public interface CityRepository extends CrudRepository<City, Integer> {
    public City findCityByName(String name);

    public boolean existsById(int id);

    public boolean existsByName(String name);
}
