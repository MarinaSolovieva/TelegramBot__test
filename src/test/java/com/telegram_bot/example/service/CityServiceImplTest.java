package com.telegram_bot.example.service;

import com.telegram_bot.example.dao.CityRepository;
import com.telegram_bot.example.exception_handling.exception.EqualCityNameException;
import com.telegram_bot.example.exception_handling.exception.TooLongDescriptionException;
import com.telegram_bot.example.model.dto.CityResponseDTO;
import com.telegram_bot.example.model.entity.City;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
class CityServiceImplTest {

    private static final String CITY_MINSK = "Минск";
    private static final String CITY_DESCRIPTION = "Прекрасный город!";

    @Mock
    private CityRepository cityDAO;
    private CityService cityService;

    @BeforeEach
    public void init(){
        cityService = new CityServiceImpl(cityDAO);
    }

    @Test
    void tesFindByName() {
        City city = new City(CITY_MINSK, CITY_DESCRIPTION);
        city.setId(1);
        when(cityDAO.findCityByName(CITY_MINSK)).thenReturn(city);

        CityResponseDTO result = cityService.findByName(CITY_MINSK);

        assertNotNull(result);
        assertEquals(new CityResponseDTO(1, CITY_MINSK, CITY_DESCRIPTION), result);
    }

    @Test
    void testDeleteById() {
        long id = 1;
        when(cityDAO.existsById(id)).thenReturn(true);

        cityService.deleteById(1);

        verify(cityDAO, times(1)).deleteById(id);
    }

    @Test
    void testSaveWithEqualsException() {
        City city = new City(CITY_MINSK, CITY_DESCRIPTION);
        city.setId(1);
        when(cityDAO.existsByName(CITY_MINSK)).thenReturn(true);

        assertThrows(EqualCityNameException.class, () -> cityService.save(city));
    }

    @Test
    void testSaveWithToLongException() {
        City city = new City(CITY_MINSK, CITY_DESCRIPTION + "sdgdfkjgbdfhgbalfgkj adfglkjhaflgjhaglhabglahbg afgqerlkgbralgjhabflgh " +
                "bqer larhbfskdjgbslgjbslhdgbldjgn;dfgjndgjsdfgjn");
        city.setId(1);
        when(cityDAO.existsByName(CITY_MINSK)).thenReturn(false);

        assertThrows(TooLongDescriptionException.class, () -> cityService.save(city));
    }

    @Test
    void testSaveWithEquals() {
        City city = new City(CITY_MINSK, CITY_DESCRIPTION);
        city.setId(1);
        when(cityDAO.existsByName(CITY_MINSK)).thenReturn(false);
        when(cityDAO.save(city)).thenReturn(city);

        CityResponseDTO result = cityService.save(city);

        assertEquals(new CityResponseDTO(1, CITY_MINSK, CITY_DESCRIPTION), result);
    }
}