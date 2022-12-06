package com.sumup.weatherservice;

import com.sumup.weatherservice.exceptions.CityDuplicatesException;
import com.sumup.weatherservice.model.CoordinatesGeocodingAPI;
import com.sumup.weatherservice.model.ResponseWeatherAPI;
import com.sumup.weatherservice.model.TempParamsWeatherAPI;
import com.sumup.weatherservice.model.Temperature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;

import java.security.InvalidParameterException;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TemperatureProcessTest {

    @Value("${appid}")
    private String appid = "5c0407291706422c4588eb9a67d67e58";

    @Mock
    private ResponseWeatherAPI responseWeatherAPI;

    @Mock
    private TempParamsWeatherAPI tempParamsWeatherAPI;

    @InjectMocks
    private TemperatureProcess temperatureProcess;

    @Test
    @DisplayName("Should throw an exception when there is no such city or country code found")
    void getLocationWhenThereIsNoSuchCityOrCountryCodeFoundThenThrowException() {
        String city = "LondonLondon";
        String countryCode = "UK";

        assertThrows(
                InvalidParameterException.class,
                () -> temperatureProcess.getLocation(city, countryCode, appid));
    }

    @Test
    @DisplayName("Should throw an exception when there are more than one city with that name")
    void getLocationWhenThereAreMoreThanOneCityWithThatNameThenThrowException() {
        assertThrows(
        CityDuplicatesException.class,
        () -> temperatureProcess.getLocation("London", "GB", appid));
    }

    @Test
    @DisplayName("Should return the location when the city and country code are valid")
    void getLocationWhenCityAndCountryCodeAreValid() {
        CoordinatesGeocodingAPI[] coordinatesGeocodingAPIs = new CoordinatesGeocodingAPI[1];
        coordinatesGeocodingAPIs[0] = new CoordinatesGeocodingAPI("23.3217359", "42.6977028");

        CoordinatesGeocodingAPI coordinatesGeocodingAPI = null;
        try {
            coordinatesGeocodingAPI = temperatureProcess.getLocation("Sofia", "BG", appid);
        } catch (CityDuplicatesException
                | ExecutionException
                | InterruptedException
                | InvalidParameterException e) {
            e.printStackTrace();
        }

        assertEquals(coordinatesGeocodingAPIs[0], coordinatesGeocodingAPI);
    }
    @Test
    @DisplayName("Should return the temperature when the latitude and longitude are valid")
    void getTemperatureWhenLatitudeAndLongitudeAreValid() throws ExecutionException, InterruptedException {
        String lat = "12.34";
        String lon = "56.78";

        Temperature result = temperatureProcess.getTempByLatAndLon(lat, lon, appid);

        assertNotNull(result);
    }


}