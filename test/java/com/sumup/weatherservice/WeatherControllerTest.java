package com.sumup.weatherservice;

import com.sumup.weatherservice.model.Temperature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WeatherControllerTest {

    @Mock
    private WeatherService weatherService;

    @InjectMocks
    private WeatherController weatherController;

    @Test
    @DisplayName("Should throw an exception when the latitude is invalid")
    void retrieveWeatherWhenLatitudeIsInvalidThenThrowException() {
        assertThrows(
                NumberFormatException.class, () -> weatherController.retrieveWeather("a", "1"));
    }

    @Test
    @DisplayName("Should throw an exception when the longitude is invalid")
    void retrieveWeatherWhenLongitudeIsInvalidThenThrowException() {
        assertThrows(
                NumberFormatException.class,
                () -> weatherController.retrieveWeather("-34.6037", "invalid"));
    }

    @Test
    @DisplayName("Should return the temperature when the latitude and longitude are valid")
    void retrieveWeatherWhenLatitudeAndLongitudeAreValid() {
        String lat = "12.34";
        String lon = "56.78";
        Temperature temperature = new Temperature();
        temperature.setCelsius(12.34);
        temperature.setFahrenheit(56.78);
        temperature.setKelvin(90.12);

        when(weatherService.getWeather(lat, lon)).thenReturn(temperature);

        Temperature result = weatherController.retrieveWeather(lat, lon);

        assertEquals(temperature, result);
    }
}