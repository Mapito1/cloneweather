package com.sumup.weatherservice;

import org.springframework.stereotype.Component;

import java.security.InvalidParameterException;

@Component
public class InputValidation {

    void validateCoordinatesInput(String lat, String lon){
        try{
            Double latitude = Double.parseDouble(lat);
            if(latitude < - 90 || latitude > 90)
                throw new InvalidParameterException("Latitude is invalid !");
        }
        catch (NumberFormatException ex){
            throw new NumberFormatException("Latitude is invalid !");
        }
        try{
            Double longtitude = Double.parseDouble(lon);
            if(longtitude < - 180 || longtitude > 180)
                throw new InvalidParameterException("Longitude is invalid !");
        }
        catch (NumberFormatException ex){
            throw new NumberFormatException("Longitude is invalid !");
        }
    }

    void validateCityAndCountryCode(String city, String countryCode) {
        if(!city.matches("[a-zA-Z]+") )
            throw new InvalidParameterException("Invalid input for city !");
        else if(!countryCode.matches("[a-zA-Z]+"))
            throw new InvalidParameterException("Invalid input for country code !");
    }
}
