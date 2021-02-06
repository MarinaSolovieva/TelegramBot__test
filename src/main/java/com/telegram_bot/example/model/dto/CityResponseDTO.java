package com.telegram_bot.example.model.dto;

import java.util.Objects;

public class CityResponseDTO {

    private final long id;
    private final String name;
    private final String description;

    public CityResponseDTO(long id, String cityName, String description) {
        this.id = id;
        this.name = cityName;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public String getCityName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CityResponseDTO that = (CityResponseDTO) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description);
    }
}
