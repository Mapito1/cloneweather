package com.sumup.weatherservice;


import com.sumup.weatherservice.model.Temperature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Service
public class WeatherService {

    @Autowired
    TemperatureProcess temperatureProcess;

    private WeatherService(){}

    public Temperature getWeather(String requestParams1, String requestParams2){
        String appid ="";

        try {
            BufferedReader reader = new BufferedReader(new FileReader("weather-service\\appID.txt"));
            appid = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return temperatureProcess.produce(requestParams1, requestParams2, appid);
    }
}