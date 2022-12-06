package com.sumup.weatherservice.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CityDuplicatesExceptionSchema {
    private String message;
    private String details;
    private String nextActions;
}
