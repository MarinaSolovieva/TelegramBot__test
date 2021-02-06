package com.telegram_bot.example.dao;

import com.telegram_bot.example.model.entity.City;
import org.springframework.data.repository.CrudRepository;

public interface CityRepository extends CrudRepository<City, Long> {

    City findCityByName(String name);

    boolean existsById(long id);

    boolean existsByName(String name);
}
