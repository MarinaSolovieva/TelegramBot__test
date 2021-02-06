package com.telegram_bot.example.model.dto;

public class CityRequestDTO {

    private final String name;
    private final String description;

    public CityRequestDTO(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
