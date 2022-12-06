package com.sumup.weatherservice;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.security.InvalidParameterException;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class WeatherControllerTest {

    @Mock
    private WeatherService weatherService;

    @InjectMocks
    private WeatherController weatherController;

    @Test
    @DisplayName("Should throw an exception when the latitude is invalid")
    void retrieveWeatherWhenLatitudeIsInvalidThenThrowException() {
        InputValidation inputValidation = new InputValidation();
        assertThrows(
                    NumberFormatException.class, () -> inputValidation.validateCoordinatesInput("a", "1"));
        assertThrows(
                InvalidParameterException.class, () -> inputValidation.validateCoordinatesInput("190", "1"));
    }

    @Test
    @DisplayName("Should throw an exception when the longitude is invalid")
    void retrieveWeatherWhenLongitudeIsInvalidThenThrowException() {
        InputValidation inputValidation = new InputValidation();
        assertThrows(
                NumberFormatException.class,
                () -> inputValidation.validateCoordinatesInput("-34.6037", "invalid"));
        assertThrows(
                InvalidParameterException.class, () -> inputValidation.validateCoordinatesInput("35", "-200"));
    }
    @Test
    @DisplayName("Should throw an exception when the city is invalid")
    void retrieveWeatherWhenCityIsInvalid() {
        InputValidation inputValidation = new InputValidation();
        assertThrows(
                InvalidParameterException.class,
                () -> inputValidation.validateCityAndCountryCode("Sofia12", "BG"));
    }
    @Test
    @DisplayName("Should throw an exception when the country code is invalid")
    void retrieveWeatherWhenCountryCodeIsInvalid() {
        InputValidation inputValidation = new InputValidation();
        assertThrows(
                InvalidParameterException.class,
                () -> inputValidation.validateCityAndCountryCode("Sofia", "412BG"));
    }
}