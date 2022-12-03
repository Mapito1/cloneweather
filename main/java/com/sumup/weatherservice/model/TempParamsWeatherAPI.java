package com.sumup.weatherservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
@Data
@AllArgsConstructor
public class TempParamsWeatherAPI {

	@JsonProperty("temp")
	private Object temp;

	@JsonProperty("temp_min")
	private Object tempMin;

	@JsonProperty("grnd_level")
	private int grndLevel;

	@JsonProperty("humidity")
	private int humidity;

	@JsonProperty("pressure")
	private int pressure;

	@JsonProperty("sea_level")
	private int seaLevel;

	@JsonProperty("feels_like")
	private Object feelsLike;

	@JsonProperty("temp_max")
	private Object tempMax;
}