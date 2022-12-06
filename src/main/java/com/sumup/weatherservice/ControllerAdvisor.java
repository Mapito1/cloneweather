package com.sumup.weatherservice;

import com.sumup.weatherservice.exceptions.CityDuplicatesException;
import com.sumup.weatherservice.exceptions.CityDuplicatesExceptionSchema;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.security.InvalidParameterException;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CityDuplicatesException.class)
    public final ResponseEntity<String> handleCityDuplicatesException(CityDuplicatesException ex) {

        CityDuplicatesExceptionSchema exceptionResponse =
                new CityDuplicatesExceptionSchema(
                        ex.getMessage(), ex.getDetails(), ex.getNextActions());

        return new ResponseEntity(exceptionResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler({ NumberFormatException.class})
    public final ResponseEntity<Object> handleNumberFormatException(NumberFormatException ex) {

        return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler({ InvalidParameterException.class})
    public final ResponseEntity<Object> handleInvalidParameterException(InvalidParameterException ex) {

        return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

}