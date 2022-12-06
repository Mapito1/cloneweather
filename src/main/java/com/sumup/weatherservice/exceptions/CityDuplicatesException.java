package com.sumup.weatherservice.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.net.UnknownServiceException;

/**
 * TODO to check how to write java docs
 */
@Data
@AllArgsConstructor
public class CityDuplicatesException extends UnknownServiceException {
    private String message;
    private String details;
    private String nextActions;
}
