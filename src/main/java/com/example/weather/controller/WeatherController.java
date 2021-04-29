package com.example.weather.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.weather.model.Weather;
import com.example.weather.service.WeatherService;

@RestController
@RequestMapping("/api/weather")
public class WeatherController 
{
	@Autowired
	public WeatherService weatherService;
	
	@RequestMapping("/byCountryAndCity/{country}/{city}")
	public Weather getWeather(@PathVariable String country,@PathVariable String city)
	{
		return weatherService.getWeatherByCityAndCountry(country,city);
	}
	
	@RequestMapping("/byLonAndLat/{lat}/{lon}")
	public Weather getWeather1(@PathVariable String lat,@PathVariable String lon)
	{
		return weatherService.getWeatherByLatitudeAndLongitude(lat,lon);	
	}
	
}
