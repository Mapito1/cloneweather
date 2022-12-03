package com.sumup.weatherservice;

import com.sumup.weatherservice.model.Temperature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/temp")
public class WeatherController {
    private final WeatherService weatherService ;

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @RequestMapping("/lat={lat}&lon={lon}")
    Temperature retrieveWeather(@PathVariable("lat") String lat, @PathVariable("lon") String lon){

        if (!Character.isDigit(lat.charAt(0)) || lat.isEmpty()) throw new NumberFormatException("Latitude is invalid");
        else if (!Character.isDigit(lon.charAt(0)) || lon.isEmpty()) throw new NumberFormatException("Longitude is invalid");

        return weatherService.getWeather(lat, lon);
    }
    @RequestMapping("/city={city}&Country-Code={countryCode}")
    Temperature retrieveWeatherByCity(@PathVariable("city") String city, @PathVariable("countryCode") String countryCode){
        return weatherService.getWeather(city, countryCode);
    }

}
