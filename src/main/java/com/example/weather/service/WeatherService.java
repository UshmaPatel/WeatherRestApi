package com.example.weather.service;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import com.example.weather.WeatherAppProperties;
import com.example.weather.model.Weather;

@Service
public class WeatherService 
{
	private static final String WEATHER_URL =
			"http://api.openweathermap.org/data/2.5/weather?q={city},{country}&APPID={key}";
	
	private static final String WEATHER_URL1 =
			"http://api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&APPID={key}";
	
	private static final Logger logger = LoggerFactory.getLogger(WeatherService.class);
	
	private final RestTemplate restTemplate;

	private final String apiKey;
	
	
	public WeatherService(RestTemplateBuilder restTemplateBuilder,
			WeatherAppProperties weatherAppProperties) {
		this.restTemplate = restTemplateBuilder.build();
		this.apiKey = weatherAppProperties.getApi().getKey();
	}
	

	@Cacheable("weather")
	public Weather getWeatherByCityAndCountry(String country, String city) {
		// TODO Auto-generated method stub
		logger.info("Requesting current weather for {}/{}", country, city);
		
		URI url = new UriTemplate(WEATHER_URL).expand(city, country, this.apiKey);
		return invoke(url, Weather.class);
	}


	@Cacheable("weather")
	public Weather getWeatherByLatitudeAndLongitude(String lat, String lon) {
		// TODO Auto-generated method stub
		logger.info("Requesting current weather for {}/{}", lat, lon);
				
		URI url = new UriTemplate(WEATHER_URL1).expand(lat, lon, this.apiKey);
		return invoke(url, Weather.class);
	}
	
	private <T> T invoke(URI url, Class<T> responseType) {
		RequestEntity<?> request = RequestEntity.get(url).accept(MediaType.APPLICATION_JSON).build();
		ResponseEntity<T> exchange = this.restTemplate.exchange(request, responseType);
		return exchange.getBody();
	}


}
