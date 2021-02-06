package com.telegram_bot.example.controller;

import com.telegram_bot.example.model.dto.CityRequestDTO;
import com.telegram_bot.example.model.dto.CityResponseDTO;
import com.telegram_bot.example.model.entity.City;
import com.telegram_bot.example.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/cities")
public class CityController {

    private final CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("")
    public CityResponseDTO getCityByName(@RequestParam String name) {
        return cityService.findByName(name);
    }

    @DeleteMapping("/{id}")
    public String deleteCityById(@PathVariable long id) {
        cityService.deleteById(id);
        return "Город с id " + id + " был удален из базы данныx";
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public CityResponseDTO saveCity(@RequestBody CityRequestDTO cityRequestDTO) {
        return cityService.save(toCityEntity(cityRequestDTO));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CityResponseDTO updateCity(@PathVariable long id, @RequestBody CityRequestDTO cityRequestDTO) {
        City city = toCityEntity(cityRequestDTO);
        city.setId(id);
        return cityService.update(city);
    }

    private City toCityEntity(CityRequestDTO city) {
        return new City(city.getName(), city.getDescription());
    }
}
