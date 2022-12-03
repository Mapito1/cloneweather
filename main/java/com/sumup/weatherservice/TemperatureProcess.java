package com.sumup.weatherservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sumup.weatherservice.model.CoordinatesGeocodingAPI;
import com.sumup.weatherservice.model.ResponseWeatherAPI;
import com.sumup.weatherservice.model.Temperature;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Objects;


@Component
public class TemperatureProcess {

    public Temperature produce(String requestParam1, String requestParam2, String appid) {

        if(Character.isDigit(requestParam1.charAt(0)))
            return getTempByLatAndLon(requestParam1, requestParam2, appid);

        CoordinatesGeocodingAPI[] response = WebClient
                .create("http://api.openweathermap.org/geo/1.0/direct?q="+requestParam1+","+ requestParam2 +"&limit=" + 2 +"&appid=" + appid)
                .get().retrieve().bodyToMono(CoordinatesGeocodingAPI[].class).block();

        assert response != null;
        return getTempByLatAndLon(response[0].getLat().toString(), response[0].getLon().toString(), appid);
    }


    private Temperature getTempByLatAndLon(String lat, String lon, String appid){
        ObjectMapper mapper = new ObjectMapper();

        String url = "https://api.openweathermap.org/data/2.5/weather?lat="+lat+"&lon="+lon+"&appid="+appid;
        WebClient.Builder client = WebClient.builder().baseUrl(url);
        Double temperature = (Double) Objects.requireNonNull(client.build().get().retrieve()
                .bodyToMono(ResponseWeatherAPI.class)
                .block())
                .getTempParamsWeatherAPI()
                .getTemp();

        return mapper.convertValue(calculateUnits(temperature), Temperature.class);
    }

    private HashMap<String, String> calculateUnits(Double kelvin){
        DecimalFormat df = new DecimalFormat("0.00");

        HashMap<String, String> units = new HashMap<>();
        units.put("kelvin", kelvin.toString());
        units.put("celsius", df.format(kelvin - 273.15) + "");
        units.put("fahrenheit", df.format(1.8 * (kelvin - 273) + 32) + "");

        return units;
    }

}
