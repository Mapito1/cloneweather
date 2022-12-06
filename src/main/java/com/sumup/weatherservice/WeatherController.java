package com.sumup.weatherservice;

import com.sumup.weatherservice.exceptions.CityDuplicatesException;
import com.sumup.weatherservice.model.Temperature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("api/v1/temp")
public class WeatherController {
    private final WeatherService weatherService;
    private final InputValidation inputValidation;

    @Autowired
    public WeatherController(WeatherService weatherService, InputValidation inputValidation) {
        this.weatherService = weatherService;
        this.inputValidation = inputValidation;
    }

    @GetMapping("/lat={lat}&lon={lon}")
    Temperature retrieveWeather(@PathVariable("lat") String lat, @PathVariable("lon") String lon) throws CityDuplicatesException, NumberFormatException, ExecutionException, InterruptedException {

        inputValidation.validateCoordinatesInput(lat, lon);

        return weatherService.getWeather(lat, lon, false);
    }
    @GetMapping("/city={city}&country-code={countryCode}")
    Temperature retrieveWeatherByCity(@PathVariable("city") String city, @PathVariable("countryCode") String countryCode) throws CityDuplicatesException, NumberFormatException, ExecutionException, InterruptedException {

        inputValidation.validateCityAndCountryCode(city, countryCode);

        return weatherService.getWeather(city, countryCode, true);
    }

}
