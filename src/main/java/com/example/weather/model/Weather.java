package com.example.weather.model;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.weather.service.WeatherService;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Weather extends WeatherEntry
{
	private String name;
	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	

	
	
	
}
