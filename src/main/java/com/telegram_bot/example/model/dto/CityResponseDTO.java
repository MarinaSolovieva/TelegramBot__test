package com.telegram_bot.example.model.dto;

public class CityResponseDTO {

    private int id;
    private String name;
    private String description;

    public CityResponseDTO(int id, String cityName, String description) {
        this.id = id;
        this.name = cityName;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getCityName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
