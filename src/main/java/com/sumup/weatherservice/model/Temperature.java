package com.sumup.weatherservice.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;

@Data
@Component
public class Temperature {
    private Double Kelvin;
    private Double Celsius;
    private Double Fahrenheit;

    public Temperature(){};

    public Temperature(Double kelvin){
        DecimalFormat df = new DecimalFormat("0.00");
        this.Kelvin = kelvin;
        this.Celsius = Double.parseDouble(df.format(kelvin - 273.15));
        this.Fahrenheit = Double.parseDouble(df.format(1.8 * (kelvin - 273) + 32));
    }
}
