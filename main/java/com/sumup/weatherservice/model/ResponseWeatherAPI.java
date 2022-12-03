package com.sumup.weatherservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
@Data
@AllArgsConstructor
public class ResponseWeatherAPI {

	@JsonProperty("main")
	@NonNull
	private final TempParamsWeatherAPI tempParamsWeatherAPI;
}