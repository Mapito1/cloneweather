# Weather Retrieval endpoint

![Java version](https://img.shields.io/badge/Java-19-orange)
![Spring Boot version](https://img.shields.io/badge/SpringBoot-v2.7.4-brightgreen)

This is a weather retrieval endpoint app build with Spring Boot. The purpose of this application is to exposes an endpoint for current temperature retrieval for a city by user's choice.
Additional information about the coding challenge can be found in this
[PNG](readme-resources/Pair_programming_challenge_Weather_Retrieval_endpoint.png).

## Prerequisites

1. Spring Boot

## Requirements

* Tiny weather retrieval service
* Temperature should be returned in 3 formats: Celsius, Fahrenheit, Kelvin.
* Temperature retrieval for a city by user's choice

## Interact with the app

In order to send a request to the app it has to be started either by running
the `WeatherServiceApplication` class.
Shortly after the app will start, and it will be able to receive requests on port `8080`. There will be two endpoints available: `/api/v1/temp/lat={lat}&lon={lon}` and `/api/v1/temp/city={city}&country-code={city-code}`. Request to the firsth one would provide json response with the current temperature at this particular location. Request to the second one could provide response with the temperature based on the condition if there are more cities with that name. If there are then the user would recieve an informative message and would be politly ask to send a request to the other endpoint. The temperature is provided in three different units `Fahrenheit, Celsius and Kelvin`.

## Input Validation

The application has input validation. Every request must comply with the format of the parameters. For example, in `/api/v1/temp/lat={lat}&lon={lon}` -> `{lat} and {lon}` should be numbers and should not be empty. On the other side with request to `/api/v1/temp/city={city}&country-code={city-code}` -> `{city} and {city-code}` should be letters.

## Comunication with other API

The application is using [OpenweatherAPI]: https://api.openweathermap.org and [GeocodingAPI]: https://openweathermap.org/api/geocoding-api. [OpenweatherAPI]: https://api.openweathermap.org is used to provide information on current temperatures and [GeocodingAPI]: https://openweathermap.org/api/geocoding-api is used to provide geocoordinates based on city input.

## Error handling

The application has a custom exception used in the special case when there are duplicated cities and it could not be garanteed that the result would be correct. Hence a custom exception was implemented which provides all the needed information for the user to be able to handle the problem.

**CityDuplicatesException.java** response:

![CityDuplicatesException](readme-resources/duplicateCitiesException.png)
