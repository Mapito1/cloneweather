package com.sumup.weatherservice;


import com.sumup.weatherservice.exceptions.CityDuplicatesException;
import com.sumup.weatherservice.model.CoordinatesGeocodingAPI;
import com.sumup.weatherservice.model.Temperature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class WeatherService {

    @Autowired
    TemperatureProcess temperatureProcess;

    @Value("${appid}")
    String appid;

    private WeatherService(){}

    public Temperature getWeather(String requestParams1, String requestParams2, Boolean locationByCityAndCode) throws CityDuplicatesException, ExecutionException, InterruptedException {

        if(locationByCityAndCode) {

            CoordinatesGeocodingAPI coordinates = temperatureProcess.getLocation(requestParams1, requestParams2, appid);

            return temperatureProcess
                    .getTempByLatAndLon(coordinates.getLat(),coordinates.getLon(), appid);
        }
        else
            return temperatureProcess
                    .getTempByLatAndLon(requestParams1, requestParams2, appid);
    }
}