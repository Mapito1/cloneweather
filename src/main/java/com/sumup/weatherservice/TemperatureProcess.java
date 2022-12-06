package com.sumup.weatherservice;

import com.sumup.weatherservice.exceptions.CityDuplicatesException;
import com.sumup.weatherservice.model.CoordinatesGeocodingAPI;
import com.sumup.weatherservice.model.ResponseWeatherAPI;
import com.sumup.weatherservice.model.Temperature;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.security.InvalidParameterException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


@Component
public class TemperatureProcess {

    public CoordinatesGeocodingAPI getLocation(String city, String countryCode, String appid) throws CityDuplicatesException, ExecutionException, InterruptedException, InvalidParameterException {

        CompletableFuture<CoordinatesGeocodingAPI[]> response = WebClient.create("http://api.openweathermap.org/geo/1.0/")
                .get().uri(uriBuilder -> uriBuilder
                        .path("direct")
                        .queryParam("q", String.format("%s,%s", city, countryCode))
                        .queryParam("limit", 2)
                        .queryParam("appid", appid).build())
                .retrieve().bodyToMono(CoordinatesGeocodingAPI[].class).toFuture();

        CoordinatesGeocodingAPI[] coordinatesGeocodingAPIs = response.get();

            if(coordinatesGeocodingAPIs.length > 1) {
                throw new CityDuplicatesException(
                        "Duplicated cities",
                        "There are more than one city with that name",
                        "Please, send request based on coordinates /api/v1/temp/lat={lat}&lon={lon}"
                );
            }
            else if(coordinatesGeocodingAPIs.length == 0)
                throw new InvalidParameterException("No such city or country code found");

        return coordinatesGeocodingAPIs[0];
    };


    public Temperature getTempByLatAndLon(String lat, String lon, String appid) throws ExecutionException, InterruptedException {

        CompletableFuture<ResponseWeatherAPI> response = WebClient.create("https://api.openweathermap.org/data/2.5/")
                .get().uri(uriBuilder -> uriBuilder
                        .path("weather")
                        .queryParam("lat", lat)
                        .queryParam( "lon", lon)
                        .queryParam("appid", appid).build())
                .retrieve().bodyToMono(ResponseWeatherAPI.class).toFuture();

        ResponseWeatherAPI responseWeatherAPI = response.get();
        Double temp = responseWeatherAPI.getTempParamsWeatherAPI().getTemp();

        return new Temperature(temp);
    }

}
